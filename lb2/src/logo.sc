;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include game.sh)
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	logo 0
)

(instance logo of LBRoom
	(properties
		picture 105
		style $000a
	)
	
	(method (init)
		(LoadMany RES_VIEW 108)
		(LoadMany RES_PIC 780)
		(LoadMany RES_SOUND 105)
		(self setRegions: 92)
		(curRoom drawPic: 780)
		(super init:)
		(sparkle init:)
		(user canControl: FALSE canInput: FALSE)
		(theMusic number: 105 flags: mNOPAUSE loop: 1 play:)
		(self setScript: sRunIt)
	)
)

(instance sRunIt of Script
	
	(method (doit)
		(Palette PALCycle 64 235 1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette PALIntensity 0 254 0)
				(curRoom drawPic: 105 10)
				(= cycles 1)
			)
			(1
				(theGame
					handsOff:
					setCursor: INVIS_CURSOR TRUE 304 172
				)
				(= seconds 6)
			)
			(2
				(if (== (theMusic prevSignal?) 20)
					(sparkle setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3
				(if (== (theMusic prevSignal?) 30)
					(sparkle x: 60 y: 145 loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(4
				(if
					(and
						(== (sparkle cel?) (sparkle lastCel:))
						(== (theMusic prevSignal?) -1)
					)
					(sparkle dispose:)
					(= cycles 1)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(5
				(curRoom newRoom: 100)
				(self dispose:)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		x 121
		y 54
		view 108
		priority 15
		signal fixPriOn
	)
)
