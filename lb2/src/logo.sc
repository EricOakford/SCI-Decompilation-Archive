;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include sci.sh)
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
		(LoadMany 128 108)
		(LoadMany 129 780)
		(LoadMany 132 105)
		(self setRegions: 92)
		(curRoom drawPic: 780)
		(super init:)
		(sparkle init:)
		(user canControl: 0 canInput: 0)
		(theMusic number: 105 flags: 1 loop: 1 play:)
		(self setScript: sRunIt)
	)
)

(instance sRunIt of Script
	(properties)
	
	(method (doit)
		(Palette palANIMATE 64 235 1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_INTENSITY 0 254 0)
				(curRoom drawPic: 105 10)
				(= cycles 1)
			)
			(1
				(theGame handsOff: setCursor: 996 1 304 172)
				(= seconds 6)
			)
			(2
				(if (== (theMusic prevSignal?) 20)
					(sparkle setCycle: End self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3
				(if (== (theMusic prevSignal?) 30)
					(sparkle x: 60 y: 145 loop: 1 cel: 0 setCycle: End self)
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
		signal $0010
	)
)
