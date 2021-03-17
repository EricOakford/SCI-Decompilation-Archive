;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include game.sh) (include "300.shm")
(use Main)
(use Plane)
(use Sound)
(use Game)
(use System)

(public
	rm300 0
)

(local
	newPlane
	slideTime =  5
)
(instance rm300 of Room
	(properties
		picture 300
		style SHOW_FADE_IN
	)
	
	(method (init)
		(Load RES_PIC
			120 125 1111 300 302 305
			310 315 320 325 330 335
			340 345 350 355 360 365
		)
		(if (not plane)
			((= plane (Plane new:)) init: 0 6 319 199 priority: 5)
		)
		(super init: &rest)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(theGame handsOn:)
		(self setScript: slideShow)
	)
	
	(method (dispose)
		(= iconsOpen FALSE)
		(theIconBar hide: 1)
		(newPlane dispose:)
		(= newPlane 0)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(or
					(& (event type?) keyDown)
					(& (event type?) mouseDown)
				)
				(not (mod ((self script?) state?) 2))
			)
			(script cue:)
			(event claimed: TRUE)
		)
		(event claimed?)
	)
)

(instance slideShow of Script

	(method (doit)
		(super doit: &rest)
		(cond 
			((<= (snd vol?) 0) (self cue:))
			((and (== state 30) (== (-- slideTime) 0))
				(= slideTime 30)
				(snd setVol: (- (snd vol?) 2))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(snd play:)
				(= seconds 8)
			)
			(1
				(proc0_4 1 4 self)
			)
			(2
				(curRoom drawPic: 302 SHOW_BLACKOUT)
				(proc0_5 0 4)
				(= seconds 8)
			)
			(3
				(proc0_4 1 4 self)
			)
			(4
				(curRoom drawPic: 305 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(5
				(proc0_4 1 4 self)
			)
			(6
				(curRoom drawPic: 310 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(7
				(proc0_4 1 4 self)
			)
			(8
				(curRoom drawPic: 315 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(9
				(proc0_4 1 4 self)
			)
			(10
				((= newPlane (Plane new:))
					init: 0 0 319 5
					picture: 125
					style: 0
					priority: 6
					drawPic: 125
				)
				(= iconsOpen TRUE)
				((theIconBar plane?) setBitmap: 110 0 0)
				(theIconBar show:)
				(theIconBar disable:)
				(curRoom drawPic: 320 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(11
				(proc0_4 1 4 self)
			)
			(12
				(curRoom drawPic: 325 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(13
				(proc0_4 1 4 self)
			)
			(14
				((theIconBar plane?) setBitmap: 200 0 0)
				(curRoom drawPic: 330 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(15
				(proc0_4 1 4 self)
			)
			(16
				(curRoom drawPic: 335 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(17
				(proc0_4 1 4 self)
			)
			(18
				(curRoom drawPic: 340 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(19
				(proc0_4 1 4 self)
			)
			(20
				(curRoom drawPic: 345 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(21
				(proc0_5 1 4 self)
			)
			(22
				(newPlane drawPic: 1111)
				((theIconBar plane?) setBitmap: 250 0 0)
				(curRoom drawPic: 350 SHOW_BLACKOUT)
				(proc0_5 0 4)
				(= seconds 8)
			)
			(23
				(proc0_4 1 4 self)
			)
			(24
				(curRoom drawPic: 355 SHOW_BLACKOUT)
				(proc0_4 0 4)
				(= seconds 8)
			)
			(25
				(proc0_5 1 4 self)
			)
			(26
				((curRoom plane?) priority: 15)
				(curRoom drawPic: 360 SHOW_BLACKOUT)
				(proc0_5 0 4)
				(= seconds 8)
			)
			(27
				(proc0_5 1 4 self)
			)
			(28
				(curRoom drawPic: 365 SHOW_BLACKOUT)
				(proc0_5 0 4)
				(= seconds 8)
			)
			(29
				(proc0_5 1 4 self)
			)
			(30)
			(31
				(snd stop: dispose:)
				(theIconBar enable:)
				(curRoom newRoom: 100)
				(self dispose:)
			)
		)
	)
)

(instance snd of Sound
	(properties
		number 300
	)
)
