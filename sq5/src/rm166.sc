;;; Sierra Script 1.0 - (do not remove this comment)
(script# 166)
(include game.sh) (include "166.shm")
(use Main)
(use Feature)
(use ForCount)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm166 0
)

(local
	local0
	[str 200]
	local201
)
(instance rm166 of Room
	(properties
		picture 36
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(super init:)
		(self setScript: sDoAll)
	)
)

(instance sDoAll of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1 number: 17 loop: -1 play:)
				(if (ego has: iFloorScrubber)
					(ego put: iFloorScrubber)
				)
				(head init:)
				(shirt init:)
				(= seconds 2)
			)
			(1
				(hands
					init:
					setLoop: 4
					setStep: 6 6
					setMotion: MoveTo 117 114 self
				)
			)
			(2
				(eyess init: setCycle: ForwardCounter 4 self)
			)
			(3
				(theGame handsOn:)
				(= local201 1)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ORDER ICON_ITEM ICON_INVENTORY)
				(DrawPic 38 PIXELDISSOLVE)
				(Message MsgGet 166 N_ROOM NULL NULL 1 @str)
				(Display @str
					p_at 40 45
					p_color 1
					p_font 999
					p_width 240
				)
				(Message MsgGet 166 N_ROOM NULL NULL 2 @str)
				(Display @str
					p_at 40 109
					p_color 1
					p_font 999
					p_width 240
				)
				(testResults init:)
			)
			(4
				(theGame handsOff:)
				(= local201 0)
				(DrawPic 36 PIXELDISSOLVE)
				(hands
					setLoop: 4
					setStep: 6 6
					setMotion: MoveTo 114 148 self
				)
			)
			(5
				(eyess dispose:)
				(hands dispose:)
				(head setLoop: 2)
				(= seconds 5)
			)
			(6
				(head setLoop: 3)
				(= seconds 1)
			)
			(7
				(SolvePuzzle f166AceTest 100)
				(PalVary PALVARYKILL)
				(PalVary PALVARYSTART 1602 1)
				(= seconds 1)
			)
			(8
				(shirt setCel: 1)
				(PalVary PALVARYKILL)
				(PalVary PALVARYSTART 1603 1)
				(= seconds 1)
			)
			(9
				(hall init:)
				(= seconds 1)
			)
			(10
				(PalVary PALVARYKILL)
				(PalVary PALVARYSTART 1604 3)
				(= seconds 4)
			)
			(11
				(theMusic1 fade: 80 10 5 0 self)
			)
			(12
				(PalVary PALVARYKILL)
				(curRoom newRoom: 165)
			)
		)
	)
)

(instance hall of View
	(properties
		x 82
		y 26
		view 160
		loop 7
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance shirt of Prop
	(properties
		x 159
		y 146
		view 160
		loop 5
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance head of Prop
	(properties
		x 139
		y 38
		view 160
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance eyess of Prop
	(properties
		x 151
		y 67
		view 160
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
		cycleSpeed 20
	)
)

(instance hands of Actor
	(properties
		x 117
		y 149
		view 160
		loop 4
		priority 7
		signal (| ignrAct fixPriOn)
		moveSpeed 1
	)
)

(instance testResults of Feature
	(properties
		y 189
		noun N_TEST_RESULTS
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if local201
					(sDoAll cue:)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
