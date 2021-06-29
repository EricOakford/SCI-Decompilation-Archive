;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh) (include "110.shm")
(use Main)
(use BalloonTalker)
(use TWRoom)
(use Print)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	intro 0
	rev1Talk 2
	rev2Talk 3
	nellieTalk 4
	tattleTalk 5
	pennyTalk 6
	gluttTalk 7
	post1Talk 8
	quibTalk 9
	vicarTalk 10
	percyTalk 14
)

(local
	local0
)
(instance intro of ADRoom

	(method (init)
		(LoadMany RES_PIC 100)
		(LoadMany RES_SOUND 111 112 113 114 115)
		(Bset 2)
		(= normalCursor INVIS_CURSOR)
		(theGame setCursor: normalCursor TRUE 304 172)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 118
						240 117
						238 68
						213 68
						221 118
						66 116
						75 68
						33 68
						31 118
						0 118
						0 0
						319 0
					yourself:
				)
		)
		(super init: &rest)
		(theIconBar disable:)
		(theIconBar disable: 5)
		(user canInput: TRUE)
		(Palette PALIntensity 0 254 0)
		(curRoom setScript: sDoClockAnimation)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance sDoClockAnimation of Script
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(directionHandler addToFront: self)
	)
	
	(method (doit &tmp sFXTickTockPrevSignal)
		(if (== state 2)
			(= sFXTickTockPrevSignal (sFXTickTock prevSignal?))
		)
		(cond 
			((and (== state 2) (== sFXTickTockPrevSignal 10))
				(ticktock cel: 1)
			)
			((and (== state 2) (== sFXTickTockPrevSignal 20))
				(ticktock cel: 0)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 99 FADEOUT)
				(LoadMany RES_VIEW 103 101 102)
				(LoadMany RES_SOUND 111 114 113 112 115)
				(= cycles 1)
			)
			(1
				(sFXPeaceful play:)
				(curRoom drawPic: 100 FADEOUT)
				(ticktock init:)
				(spinningHands init:)
				(= seconds 1)
			)
			(2
				(sFXTickTock play:)
				(= seconds 5)
			)
			(3
				(sFXSpin play:)
				(= cycles 1)
			)
			(4
				(spinningHands setCycle: EndLoop self)
			)
			(5
				(curlyHands init:)
				(spinningHands dispose:)
				(= cycles 1)
			)
			(6
				(curlyHands setCycle: EndLoop self)
			)
			(7
				(clockface init:)
				(ticktock dispose:)
				(= cycles 1)
			)
			(8
				(sFXSprings play:)
				(sFXSpin stop:)
				(sFXTickTock stop:)
				(sFXPeaceful stop:)
				(= cycles 1)
			)
			(9
				(clockface setCycle: EndLoop self)
			)
			(10
				(curlyHands stopUpd:)
				(clockface stopUpd:)
				(= cycles 1)
			)
			(11
				(title init: setStep: 1 12)
				(= cycles 1)
			)
			(12
				(title setMotion: MoveTo 144 18 self)
			)
			(13
				(sFXDrop play:)
				(title setMotion: MoveTo 144 8 self)
			)
			(14
				(title setMotion: MoveTo 144 18 self)
			)
			(15
				(title setMotion: MoveTo 144 15 self)
			)
			(16 (= seconds 6))
			(17 (= cycles 2))
			(18
				(client setScript: sFirstScreenInput)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(< state 17)
				(OneOf (event type?) mouseDown keyDown joyDown)
				(not (event modifiers?))
			)
			(event claimed: TRUE)
			(ticktock addToPic:)
			(spinningHands addToPic:)
			(sFXTickTock fade:)
			(sFXPeaceful fade:)
			(self changeState: 17)
		)
		(event claimed: TRUE)
	)
)

(instance sFirstScreenInput of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(= normalCursor INVIS_CURSOR)
				(theGame setCursor: ARROW_CURSOR TRUE 100 100)
				(switch
					(Print
						addText: {What do you feel like doing?}
						font: userFont
						addButton: 1 N_ROOM NULL C_MENU 3 0 12
						addButton: 2 N_ROOM NULL C_MENU 4 0 26
						addButton: 3 N_ROOM NULL C_MENU 5 0 40
						init:
					)
					(1
						(client setScript: sDoPickAnAct)
					)
					(2
						(= normalCursor ARROW_CURSOR)
						(theGame restore:)
						(-- state)
						(= cycles 2)
					)
					(3 (= quit TRUE))
					(else 
						(client setScript: sDoPickAnAct)
					)
				)
			)
		)
	)
)

(instance sDoPickAnAct of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theGame setCursor: ARROW_CURSOR TRUE 100 100)
				(= register 120)
				(= gameAct 1)
				(curRoom newRoom: 601)
			)
		)
	)
)

(instance sDoActChange of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: INVIS_CURSOR TRUE 100 100)
				(= cycles 1)
			)
			(1 (curRoom newRoom: 601))
		)
	)
)

(instance spinningHands of Prop
	(properties
		x 153
		y 147
		view 102
		priority 15
		signal fixPriOn
	)
)

(instance clockface of Prop
	(properties
		x 158
		y 184
		view 101
		loop 1
		priority 11
		signal fixPriOn
	)
)

(instance ticktock of Prop
	(properties
		x 157
		y 184
		view 102
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance curlyHands of Prop
	(properties
		x 154
		y 148
		view 101
		priority 15
		signal fixPriOn
	)
)

(instance title of Actor
	(properties
		name {twisty}
		x 144
		y -50
		view 103
		signal ignrHrz
	)
)

(instance sFXPeaceful of Sound
	(properties
		flags mNOPAUSE
		number 111
		loop -1
	)
)

(instance sFXTickTock of Sound
	(properties
		flags mNOPAUSE
		number 112
		loop -1
	)
)

(instance sFXSpin of Sound
	(properties
		flags mNOPAUSE
		number 113
	)
)

(instance sFXSprings of Sound
	(properties
		flags mNOPAUSE
		number 114
	)
)

(instance sFXDrop of Sound
	(properties
		flags mNOPAUSE
		number 115
	)
)

(instance pennyP of Actor
	(properties
		x 170
		y 46
		view 107
		loop 1
	)
)

(instance postal of Actor
	(properties
		x 123
		y 99
		view 107
		priority 8
		signal fixPriOn
	)
)

(instance percy of Actor
	(properties
		x 75
		y 128
		view 110
		loop 1
		signal ignrAct
	)
)

(instance glutton of Actor
	(properties
		x 72
		y 77
		view 110
		loop 4
	)
)

(instance nellie of Actor
	(properties
		x 223
		y 75
		view 110
		loop 5
	)
)

(instance tattle of Actor
	(properties
		x 179
		y 55
		view 106
		loop 3
	)
)

(instance willRev of Actor
	(properties
		x 113
		y 167
		view 106
		loop 2
	)
)

(instance nedRev of Actor
	(properties
		x 162
		y 169
		view 106
		loop 1
	)
)

(instance goody of Actor
	(properties
		x 65
		y 80
		view 106
	)
)

(instance quibble of Actor
	(properties
		x 142
		y 116
		view 104
		loop 1
	)
)

(instance vicar of Actor
	(properties
		x 171
		y 115
		view 104
		loop 2
	)
)

(instance rev1Talk of BalloonTalker
	(properties
		x 174
		y 118
		talkWidth 120
	)
)

(instance rev2Talk of BalloonTalker
	(properties
		x 129
		y 120
		talkWidth 150
	)
)

(instance nellieTalk of BalloonTalker
	(properties
		x 60
		y 59
		talkWidth 150
		tailPosn 4
	)
)

(instance tattleTalk of BalloonTalker
	(properties
		x 6
		y 59
		talkWidth 150
		tailPosn 4
	)
)

(instance pennyTalk of BalloonTalker
	(properties
		x 27
		y 52
		talkWidth 120
		tailPosn 4
	)
)

(instance gluttTalk of BalloonTalker
	(properties
		x 76
		y 53
		talkWidth 150
		tailPosn 3
	)
)

(instance post1Talk of BalloonTalker
	(properties
		x 124
		y 56
		talkWidth 180
	)
)

(instance quibTalk of BalloonTalker
	(properties
		x 6
		y 66
		talkWidth 120
		tailPosn 1
	)
)

(instance vicarTalk of BalloonTalker
	(properties
		x 169
		y 67
		talkWidth 100
	)
)

(instance percyTalk of BalloonTalker
	(properties
		x 192
		y 79
		talkWidth 100
	)
)
