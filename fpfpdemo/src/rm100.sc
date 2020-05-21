;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh) (include "100.shm")
(use Main)
(use FPRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(instance rm100 of FPRoom
	(properties
		picture 100
		style FADEOUT
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theIconBar disable:)
		(LoadMany RES_VIEW 100)
		(LoadMany RES_SOUND 100)
		(super init:)
		(sparkle init:)
		(theMusic
			number: (if (> numVoices 11) 100 else 1100)
			flags: mNOPAUSE
			loop: 1
			play:
		)
		(self setScript: sLogoCartoon)
	)
	
	(method (dispose)
		(super dispose:)
		(theMusic fade: 0 5 5 1)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(event claimed: TRUE)
			(= timeOver 0)
			(theGame setCursor: ARROW_CURSOR TRUE)
			(curRoom newRoom: 105)
		)
	)
)

(instance sLogoCartoon of Script
	(properties)
	
	(method (doit)
		(Palette PALCycle 95 224 1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Palette PALIntensity 0 254 0)
				(theGame setCursor: INVIS_CURSOR TRUE 310 180)
				(= seconds 6)
			)
			(1
				(if (== (theMusic prevSignal?) 20)
					(sparkle setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(2
				(if (== (theMusic prevSignal?) 30)
					(sparkle x: 60 y: 145 loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3
				(if
					(and
						(== (sparkle cel?) (sparkle lastCel:))
						(== (theMusic prevSignal?) -1)
					)
					(= cycles 2)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(4
				(curRoom newRoom: 105)
				(self dispose:)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		x 121
		y 54
		view 100
		priority 15
		signal fixPriOn
	)
)
