package frontend.painter.interceptor

import frontend.painter.Painter

abstract class Interceptor(painter: Painter) : Painter

abstract class MultiInterceptor(painters: List<Painter>) : Painter