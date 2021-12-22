;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
(use Main)
(use LarryRoom)
(use n098)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(instance rm100 of LarryRoom
	(properties
		picture 100
		style $000e
		noControls 1
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theIconBar disable:)
		(thePlane setRect: 0 0 319 199)
		(UpdatePlane thePlane)
		(super init:)
		(SetCursor 98 0 0)
		(if (theMusic2 handle?) (theMusic2 number: 0 stop:))
		(theMusic number: 100 loop: 1 play: setVol: 127)
		(PalCycle 0 80 234 1)
		(sierra init: setPri: 200)
		(self setScript: sLogoCartoon 0 0)
	)
	
	(method (dispose)
		(thePlane setRect: 0 15 320 154)
		(theMusic fade:)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event type?) (event claimed: 1) (proc98_0))
	)
)

(instance sLogoCartoon of Script
	(properties)
	
	(method (doit)
		(if (not register) (PalCycle 1 80 1))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== (theMusic prevSignal?) 50)
						(-- state)
						(theMusic prevSignal: 0)
						(sierra setCycle: End self)
					)
					((== (theMusic prevSignal?) -1) (theMusic prevSignal: 0) (= seconds 4))
					(else (-- state) (= cycles 1))
				)
			)
			(1
				(thePlane drawPic: -2)
				(curRoom newRoom: 110)
				(self dispose:)
			)
		)
	)
)

(instance sierra of Prop
	(properties
		x 163
		y 190
		view 108
	)
)
