;;; Sierra Script 1.0 - (do not remove this comment)
(script# LOGOROOM)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	LogoRoom 0
)

(local
	timeToCycle
)
(instance LogoRoom of Room
	(properties
		picture pHalfDome
		style FADEOUT
	)
	
	(method (init)
		(SetPort 0 0 SCRNHIGH SCRNWIDE 0 0)
		(theIconBar disable:)
		(theGame setCursor: ARROW_CURSOR FALSE)
		(Bset fHideCursor)
		(Palette PALIntensity 0 254 0)
		(super init:)
		(self setScript: logoScript)
		(glint init:)
		(glint2 init:)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(if (== (mod (++ timeToCycle) 3) 0)
			(Palette PALCycle 95 218 -1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(OneOf (event message?) ESC ENTER)
			)
			(if script
				(logoScript changeState: 8)
			)
			(event claimed: TRUE)
		)
	)
)

(instance glint of Prop
	(properties
		x 134
		y 34
		view vHalfSparkle
		cycleSpeed 2
	)
)

(instance glint2 of Prop
	(properties
		x 60
		y 155
		view vHalfSparkle
		loop 1
		cycleSpeed 2
	)
)

(instance logoSound of Sound
	(properties
		number sHalfDome
	)
)

(instance logoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(logoSound
					number: sHalfDome
					loop: 1
					flags: 0
					play: self
				)
			)
			(2)
			(3
				(glint setCycle: EndLoop self)
			)
			(4
				(glint dispose:)
			)
			(5
				(glint2 setCycle: EndLoop self)
			)
			(6
				(glint2 dispose:)
			)
			(7
				(= ticks 60)
			)
			(8
				(logoSound stop:)
				(SetPort 0 0 190 SCRNWIDE 10 0)
				(curRoom newRoom: NOTICE2)
				(self dispose:)
			)
		)
	)
)
