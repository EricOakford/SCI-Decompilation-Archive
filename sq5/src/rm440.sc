;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use PriorityTalker)
(use Print)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm440 0
	roger 15
	pukoid 19
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6 =  5
	[local7 6] = [182 93 180 92 190 120]
)
(instance theMusic4 of Sound
	(properties)
)

(instance rm440 of Rm
	(properties
		picture 83
		style $0064
	)
	
	(method (init)
		(LoadMany 128 459 458)
		(theLookLeft init: setOnMeCheck: 1 2)
		(theLookRight init: setOnMeCheck: 1 4)
		(theLookStraight init: setOnMeCheck: 1 8)
		(directionHandler addToFront: self)
		(ego view: 0 init: dispose:)
		(theIconBar disable: 0 3 4 5 6 1)
		(switch prevRoomNum
			(420
				(curRoom setScript: sInitRoom)
			)
			(else 
				(curRoom setScript: sInitRoom)
			)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(if (== (theIconBar curIcon?) (theIconBar at: 2))
			(cond 
				((InRect 0 25 174 163 mouseX mouseY) (theGame setCursor: 3270))
				((InRect 175 22 315 162 mouseX mouseY) (theGame setCursor: 3272))
				(else
					((theIconBar at: 2) cursor: 982)
					(theGame setCursor: ((theIconBar at: 2) cursor?))
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theIconBar enable:)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp eventMessage temp1)
		(if (User controls?)
			(cond 
				(
					(and
						(= eventMessage (event message?))
						(& (event type?) evKEYBOARD)
					)
					(switch eventMessage
						(JOY_RIGHT
							(SetCursor 250 100)
							(= mouseX 250)
							(= mouseY 100)
							(event claimed: 1)
							(curRoom setScript: sLook 0 2)
						)
						(JOY_LEFT
							(SetCursor 100 100)
							(= mouseX 100)
							(= mouseY 100)
							(event claimed: 1)
							(curRoom setScript: sLook 0 1)
						)
						(else 
							(event claimed: 0)
							(super handleEvent: event &rest)
						)
					)
				)
				(
					(and
						(= eventMessage (event message?))
						(& (event type?) evJOYSTICK)
					)
					(switch eventMessage
						(JOY_RIGHT
							(SetCursor 250 100)
							(= mouseX 250)
							(= mouseY 100)
							(event claimed: 1)
							(curRoom setScript: sLook 0 2)
						)
						(JOY_LEFT
							(SetCursor 100 100)
							(= mouseX 100)
							(= mouseY 100)
							(event claimed: 1)
							(curRoom setScript: sLook 0 1)
						)
						(else 
							(event claimed: 0)
							(super handleEvent: event &rest)
						)
					)
				)
				(else (event claimed: 0) (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
		(event claimed?)
	)
)

(instance sInitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pukoidHead init:)
				(rogHead init:)
				(= cycles 2)
			)
			(1
				(rogHead stopUpd:)
				(= cycles 2)
			)
			(2 (messager say: 2 0 0 1 self))
			(3
				(rogHead stopUpd:)
				(= cycles 2)
			)
			(4 (messager say: 2 0 0 2 self))
			(5
				(pukoidHead stopUpd:)
				(= cycles 2)
			)
			(6 (messager say: 2 0 0 3 self))
			(7
				(rogHead stopUpd:)
				(= cycles 2)
			)
			(8 (messager say: 2 0 0 4 self))
			(9
				(pukoidHead stopUpd:)
				(= cycles 2)
			)
			(10
				(messager say: 2 0 0 5 self)
			)
			(11
				(pukoidHead stopUpd:)
				(= cycles 2)
			)
			(12
				(messager say: 2 0 0 6 self)
			)
			(13
				(theGame handsOn:)
				(= cycles 1)
			)
			(14
				(theIconBar disable: 0 3 4 5 6 1)
				(theIconBar curIcon: (theIconBar at: 2))
				(= cycles 1)
			)
			(15
				(pukoidHead startUpd:)
				(rogHead startUpd: setScript: sDrool)
				(drool init:)
				(= cycles 1)
			)
			(16 (self dispose:))
		)
	)
)

(instance sLook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(1
						(if (== local3 0) (= local4 1))
						(= local0 1)
						(if local3 else 2)
						(rogHead loop: 1 cel: 0 x: 147 y: 111)
					)
					(2
						(if (== local3 0) (= local4 2))
						(if local3 else 1)
						(= local0 2)
						(rogHead loop: 2 cel: 0 x: 146 y: 110)
					)
					(3
						(= local0 0)
						(rogHead setLoop: 0 cel: 0 x: 145 y: 110)
					)
				)
				(= cycles 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance sDrool of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(theMusic4 number: 461 loop: 1 play:)
				(= seconds 1)
			)
			(2
				(pukoidHead init:)
				(drool init: show: setCycle: End self)
			)
			(3
				(drool setCycle: Beg)
				(= local5 (* local2 2))
				(drip
					init:
					show:
					setPri: 4
					setMotion: MoveTo [local7 local5] [local7 (+ local5 1)] self
				)
			)
			(4
				(if (== local0 local2)
					(= local1 1)
					(drip hide:)
					(theGame handsOff:)
					(self setScript: sDrooledOn self)
				else
					(switch local2
						(0
							(if (== local0 2)
								(drip setPri: 2 setMotion: MoveTo 193 144 self)
							else
								(drip setPri: 4 setMotion: MoveTo 193 144 self)
							)
						)
						(2
							(drip setPri: 5 setMotion: MoveTo 193 155 self)
						)
						(1
							(drip setPri: 2 setMotion: MoveTo 189 130 self)
						)
					)
				)
			)
			(5
				(if (== local1 1)
					(= cycles 1)
				else
					(splat init:)
					(spray init:)
					(= cycles 1)
				)
			)
			(6
				(if (== local1 1)
					(self dispose:)
				else
					(splat addToPic:)
					(drip hide:)
					(= local3 0)
					(= local4 0)
					(if (== (= local6 (- local6 1)) 0)
						(self setScript: sShootPukoid)
					else
						(= state -1)
						(= cycles 1)
					)
				)
			)
		)
	)
)

(instance sShootPukoid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rogHead loop: 2 cel: 0 x: 146 y: 110)
				(pukoidHead setCel: 1)
				(blast1 init: setCycle: Fwd)
				(blast2 init:)
				(theMusic4 number: 402 setLoop: 1 play:)
				(= seconds 2)
			)
			(1
				(blast1 hide:)
				(blast2 hide:)
				(= seconds 1)
			)
			(2
				(theMusic4 number: 402 setLoop: 1 play:)
				(blast1 show:)
				(blast2 show:)
				(wince init:)
				(= seconds 1)
			)
			(3
				(blast1 hide:)
				(blast2 hide:)
				(= seconds 1)
			)
			(4 (curRoom newRoom: 420))
		)
	)
)

(instance sDrooledOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local0
					(0
						(drip
							show:
							setPri: 5
							view: 459
							setLoop: 7
							cel: 0
							x: 183
							y: 98
						)
						(= seconds 2)
					)
					(1
						(rogHead setLoop: 1 cel: 1 x: 147 y: 111)
						(= seconds 1)
					)
					(2
						(rogHead setLoop: 2 cel: 1 x: 146 y: 110)
						(= seconds 1)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(switch local0
					(0
						(ooze init: setCycle: End self)
					)
					(1
						(drip
							show:
							setLoop: 7
							cel: 0
							x: 164
							y: 96
							setCycle: End self
						)
					)
					(2
						(drip
							show:
							setLoop: 7
							cel: 0
							x: 193
							y: 129
							setCycle: End self
						)
					)
					(else  (= cycles 1))
				)
			)
			(2 (= seconds 1))
			(3
				(drip hide:)
				(eyescross hide: dispose:)
				(rogHead
					setLoop: 5
					cel: 0
					x: 145
					y: 110
					cycleSpeed: 10
					setCycle: End self
				)
				(= cycles 3)
			)
			(4 (= seconds 1))
			(5
				(= local0 0)
				(theMusic1 fade:)
				(EgoDead 24)
			)
		)
	)
)

(instance pukoidHead of Prop
	(properties
		x 150
		y 84
		view 458
		cel 1
		priority 10
	)
	
	(method (init)
		(switch local0
			(0
				(self setLoop: 0 cel: 1 x: 150 y: 84 setPri: 10)
				(= local2 0)
			)
			(1
				(self setLoop: 0 cel: 0 x: 150 y: 84)
				(= local2 1)
			)
			(2
				(self setLoop: 0 cel: 2 x: 150 y: 84)
				(= local2 2)
			)
		)
		(super init: &rest)
	)
)

(instance wince of Prop
	(properties
		x 157
		y 39
		view 458
		loop 6
		cel 1
		priority 11
		signal $0010
	)
)

(instance rogHead of Prop
	(properties
		x 145
		y 110
		view 459
		priority 3
		signal $0010
	)
	
	(method (init)
		(self setPri: 3)
		(super init: &rest)
	)
)

(instance eyescross of View
	(properties
		x 145
		y 110
		view 459
		loop 3
		priority 4
		signal $4010
	)
)

(instance ooze of Prop
	(properties
		x 177
		y 100
		view 459
		loop 9
		priority 5
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 9 cel: 0 cycleSpeed: 10)
	)
)

(instance drool of Actor
	(properties
		x 183
		y 67
		view 458
		loop 4
		priority 6
		signal $4010
	)
	
	(method (init)
		(switch local0
			(0
				(self setLoop: 4 cel: 0 x: 182 y: 74)
			)
			(1
				(self setLoop: 4 cel: 0 x: 176 y: 72)
			)
			(2
				(self setLoop: 4 cel: 0 x: 185 y: 79)
			)
		)
		(super init: &rest)
	)
)

(instance drip of Actor
	(properties
		view 459
		loop 6
		priority 6
		signal $4010
	)
	
	(method (init)
		(switch local0
			(0
				(self setLoop: 6 cel: 0 x: 182 y: 74 setStep: 3 9)
			)
			(1
				(self setLoop: 6 cel: 0 x: 176 y: 72 setStep: 3 9)
			)
			(2
				(self setLoop: 6 cel: 0 x: 185 y: 79 setStep: 3 9)
			)
		)
		(super init: &rest)
	)
)

(instance splat of Prop
	(properties
		view 459
		loop 7
		priority 2
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPri: (drip priority?)
			x: (drip x?)
			y: (drip y?)
		)
	)
)

(instance spray of Prop
	(properties
		view 459
		loop 8
		priority 2
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPri: (drip priority?)
			x: (drip x?)
			y: (drip y?)
		)
	)
)

(instance blast1 of Prop
	(properties
		x 102
		y 62
		view 458
		loop 5
		priority 4
		signal $0010
	)
	
	(method (init)
		(self view: 458 loop: 5 cel: 0 x: 104 y: 63)
		(super init:)
	)
)

(instance blast2 of Prop
	(properties
		x 197
		y 25
		view 458
		loop 6
		priority 4
		signal $4010
	)
	
	(method (init)
		(self view: 458 setLoop: 6 cel: 0 x: 197 y: 25)
		(super init:)
	)
)

(instance pukoid of PriorityTalker
	(properties
		x 117
		y 24
		view 458
		cel 1
		priority 10
		signal $4000
		talkWidth 180
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 160
			tailY: 35
			xOffset: -40
			isBottom: 1
		)
		(rogHead stopUpd:)
		(super init: 0 peyes pmouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
	
	(method (display theText &tmp theTalkWidth newSystemWindow)
		(= theTalkWidth talkWidth)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(if (and (not (HaveMouse)) (!= theCursor 996))
			(= saveCursor theCursor)
			(theGame setCursor: 996)
		else
			(= saveCursor 0)
		)
		(if showTitle (Print addTitle: name))
		(Print
			window: newSystemWindow
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 1
			init:
		)
	)
)

(instance pmouth of Prop
	(properties
		nsTop 44
		nsLeft 46
		view 458
		loop 1
		cel 3
		priority 7
		signal $0010
	)
)

(instance peyes of Prop
	(properties
		nsTop 31
		nsLeft 72
		view 458
		loop 3
		cel 1
		priority 5
		signal $0010
	)
)

(instance roger of PriorityTalker
	(properties
		x 140
		y 75
		view 459
		priority 4
		signal $4000
		talkWidth 180
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 170
			tailY: 135
			xOffset: -20
			isBottom: 0
		)
		(pukoidHead stopUpd:)
		(super init: 0 0 rmouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
	
	(method (display theText &tmp theTalkWidth newSystemWindow)
		(= theTalkWidth talkWidth)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(if (and (not (HaveMouse)) (!= theCursor 996))
			(= saveCursor theCursor)
			(theGame setCursor: 996)
		else
			(= saveCursor 0)
		)
		(if showTitle (Print addTitle: name))
		(Print
			window: newSystemWindow
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 1
			init:
		)
	)
)

(instance rmouth of Prop
	(properties
		nsTop 29
		nsLeft 29
		view 459
		loop 4
		cel 6
		priority 5
		signal $0010
	)
)

(instance theLookLeft of Feature
	(properties
		x 200
		y 170
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= local0 1)
					(curRoom setScript: sLook 0 1)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theLookRight of Feature
	(properties
		x 200
		y 170
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= local0 2)
					(curRoom setScript: sLook 0 2)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theLookStraight of Feature
	(properties
		x 200
		y 170
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= local0 0)
					(curRoom setScript: sLook 0 3)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
