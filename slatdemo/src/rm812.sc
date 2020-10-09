;;; Sierra Script 1.0 - (do not remove this comment)
(script# 812)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm812 0
)

(instance rm812 of Room
	(properties
		picture 812
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(sparkle init:)
		(aDemoSound
			number: 1
			flags: mNOPAUSE
			loop: 1
			play:
		)
		(self setScript: sLogoCartoon)
	)
	
	(method (dispose)
		(aDemoSound dispose:)
		(super dispose:)
	)
)

(instance sLogoCartoon of Script
	
	(method (doit)
		(theGame setCursor: INVIS_CURSOR TRUE 310 180)
		(Palette PALCycle 112 223 -1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Palette PALIntensity 0 254 0)
				(= seconds 6)
			)
			(1
				(if (== (aDemoSound prevSignal?) 20)
					(sparkle setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(2
				(if (== (aDemoSound prevSignal?) 30)
					(sparkle
						x: 60
						y: 155
						loop: 1
						setCel: 0
						setCycle: EndLoop self
					)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3
				(if
					(and
						(== (sparkle cel?) (sparkle lastCel:))
						(== (aDemoSound prevSignal?) -1)
					)
					(= cycles 2)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(4
				(curRoom newRoom: 13)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		x 131
		y 54
		view 10
		priority 15
		signal fixPriOn
	)
)

(instance aDemoSound of Sound)
