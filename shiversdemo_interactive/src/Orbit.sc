;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ORBIT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:
;;;;
;;;;	Causes its client to orbit about a point or actor.
;;;;	Usage: (theActor setMotion: Orbit
;;;;					theCenterObj
;;;;					theRadius
;;;;					theXTilt
;;;;					theYTilt
;;;;					theStep		(in degrees, default is 10)
;;;;					theWinding	(1 = clockwise, -1 counterclockwise)
;;;;					theAngle		(0 = north, etc)
;;;;
;;;;	Classes:
;;;;		Orbit


(script# ORBIT)
(include game.sh)
(use Main)
(use Motion)
(use System)


(class Orbit kindof Motion
	(properties
		centerObj	0			;center x and y are properties
		radius		50
		xTilt			0			;0=flat circular view 90=edge-on
		yTilt			0			;0=flat circular view 90=edge-on
		angleStep	10			;angle degree increment per animation cycle
		winding		1			;clockwise=1 counterclockwise=-1
		curAngle		0			;0=north, etc.
	)
	
	(method 
		(init 
			theObj theCtrObj theRadius 							;main args
			theXTilt theYTilt	theStep theWinding theAngle	;exotic args
			&tmp centerX centerY deltaX deltaY
		)
		
		;Initialization portion: if no arg given leave corresponding
		;property alone.
		
		(if (>= argc 1)								(= client		theObj)
			(if (>= argc 2)							(= centerObj	theCtrObj)		
				(if (>= argc 3)						(= radius		theRadius)
					(if (>= argc 4)					(= xTilt 		theXTilt)
						(if (>= argc 5)				(= yTilt 		theYTilt)
							(if (>= argc 6)			(= angleStep	theStep)
								(if (>= argc 7) 		(= winding 		theWinding)
									(if (>= argc 8)	(= curAngle 	theAngle))
		)))))))
		
		
		(if centerObj
			(= centerX (centerObj x?))						;track a moving center
			(= centerY (centerObj y?))
		else
			(= centerX (/ screenWidth	2))				;or use screen center
			(= centerY (/ screenHeight 2))
		)
		
		(= deltaX (SinMult curAngle radius))
		(= deltaY 
			(CosMult (+ yTilt perspective)
				(CosMult curAngle radius)
			)
		)
		(if xTilt
			(=  deltaX	(CosMult xTilt deltaX))
			(+= deltaY	(SinMult xTilt deltaX))
		)
		
		(= x 	(+ centerX deltaX))								;compute destination
		(= y	(-	centerY deltaY))
		
		(= curAngle 
			(umod (+ curAngle (* winding angleStep)) 	;angle for next step
				360
			)
		)
		
		(super init: client x y)							;Initialize the motion
		
	)
	
	(method (moveDone)
		(self init:)		;proceed with next step, same properties
	)
)