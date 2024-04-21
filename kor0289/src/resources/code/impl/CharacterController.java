@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired SessionHandler sessionHandler;
    @Autowired CharacterService characterService;
    @Autowired AdventureService adventureService;

    @GetMapping("/{id}")
    public ResponseEntity<Character> findById(
            @PathVariable int id,
            @RequestParam(required = false, defaultValue = "") List<String> include,
            @RequestParam(required = false, defaultValue = "false") boolean lazy,
            HttpServletRequest request
    ) {
        Session session = sessionHandler.getSessionFromRequest(request);
        CharacterDTO entity = characterService.findById(id);
        AdventureDTO adventure = adventureService.findById(entity.getIdAdventure());

        if (!session.hasAccess(adventure.getIdLicense())) {
            throw new RestException(RestError.of(HttpStatus.FORBIDDEN, "You are not authorized to access this resource!"));
        }

        if (!lazy) {
            Initialization.hibernateInitializeAll(entity);
        } else {
            Initialization.hibernateInitializeAll(entity, include);
        }

        return new ResponseEntity<>(Character.fromDTO(entity), HttpStatus.OK);
    }

}
