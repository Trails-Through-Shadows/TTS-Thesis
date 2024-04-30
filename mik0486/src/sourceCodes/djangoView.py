# -*- encoding: utf-8 -*-

from django.contrib.auth.decorators import login_required
from django.shortcuts import render

@login_required(login_url="/login/")
def index(request):
    return render(request, 'home/dashboard.html')

@login_required(login_url="/login/")
def partViewer(request):
    return render(request, 'dungeon/parts/partsViewer.html')

@login_required(login_url="/login/")
def parkWorkbench(request, id=None):
    context = {"partId": id}
    return render(request, 'dungeon/parts/partsWorkbench.html', context)