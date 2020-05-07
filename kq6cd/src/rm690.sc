;;; Sierra Script 1.0 - (do not remove this comment)
(script# 690)
(include sci.sh)
(use Main)
(use rgDead)
(use KQ6Print)
(use KQ6Room)
(use Conv)
(use MoveCyc)
(use Feature)
(use LoadMany)
(use Window)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm690 0
)

(local
	local0
	local1
	narratorX
	narratorY
	narratorTalkWidth
	[local5 81] = [2 0 8 55 2 1 17 58 2 2 21 59 2 3 32 58 2 4 43 53 2 5 53 50 2 6 59 51 2 7 65 52 2 8 73 53 2 9 85 56 2 10 87 61 2 11 89 72 3 0 282 55 3 1 291 56 3 1 291 56 3 1 291 56 3 2 302 57 3 3 313 57 3 4 318 57 3 5 318 59 -32768]
	[local86 57] = [4 0 311 105 4 1 302 107 4 2 293 111 4 3 278 112 4 4 269 123 5 0 83 108 5 1 75 111 5 2 66 113 5 3 54 113 5 4 44 118 5 5 33 117 5 6 22 118 5 7 10 119 5 8 3 115 -32768]
)
(instance rm690 of KQ6Room
	(properties
		noun 3
		picture 690
		south 680
	)
	
	(method (init)
		(Load rsVIEW 691)
		(flame init: setCycle: Forward)
		(alex init:)
		(lord init:)
		(leftarm init:)
		(rightarm init:)
		(deadHand init:)
		(deadFist init:)
		(deadEyes init: setScript: eyeScript)
		(theGame handsOff:)
		(theIconBar disable: 0)
		(self setScript: introScript)
		(super init: &rest)
	)
	
	(method (newRoom)
		(introGhost setCycle: 0)
		(LoadMany 0 942)
		(super newRoom: &rest)
	)
)

(instance sfx of Sound
	(properties)
)

(instance alex of Actor
	(properties
		x 127
		y 144
		noun 2
		view 691
		priority 2
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31
				(messager say: noun theVerb 0)
			)
			(1
				(messager say: noun theVerb 0)
			)
			(5
				(messager say: noun theVerb 0)
			)
			(else  (messager say: noun 0 0))
		)
	)
)

(instance flame of Actor
	(properties
		x 22
		y 84
		view 690
		loop 1
		cycleSpeed 10
	)
)

(instance lord of Feature
	(properties
		noun 4
		onMeCheck $0006
	)
	
	(method (doVerb theVerb)
		(if
		(OneOf theVerb 28 8 14 30 31 47 50 1 32 65 68 33 70 16 37)
			(messager say: noun theVerb)
		else
			(switch theVerb
				(5
					(theGame handsOff:)
					(curRoom setScript: alexHand)
				)
				(48
					(theGame handsOff:)
					(theGame givePoints: 2)
					(curRoom setScript: issueChallenge)
				)
				(13
					(if local0
						(messager say: noun theVerb 5)
					else
						(theGame handsOff:)
						(theGame givePoints: 4)
						(curRoom setScript: holdUpMirror)
					)
				)
				(42
					(if local0
						(messager say: noun theVerb 5)
					else
						(messager say: noun theVerb 6)
					)
				)
				(2
					(if local0
						(messager say: noun theVerb 5)
					else
						(messager say: noun theVerb 6)
					)
				)
				(else 
					(if local0
						(messager say: noun 0 5)
					else
						(messager say: noun 0 6)
					)
				)
			)
		)
	)
)

(instance introScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1 (messager say: 1 0 1 0 self))
			(2
				(= local0 1)
				(introGhost init: setCycle: MoveCycle @local5 introGhost)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= seconds 15)
			)
			(3
				(theGame handsOff:)
				(= local0 0)
				(messager say: 1 0 2 0 self)
			)
			(4 (= cycles 1))
			(5
				(rgDead stateOf690: 1)
				(client setScript: deadTouch 0 1)
			)
		)
	)
)

(instance rightarm of Actor
	(properties
		x 145
		y 144
		view 691
		loop 1
		priority 2
		signal $4010
		cycleSpeed 1
	)
)

(instance leftarm of Actor
	(properties
		x 110
		y 147
		view 691
		loop 2
		priority 2
		signal $4010
	)
)

(instance deadTouch of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 5) (== (theMusic prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 682 loop: 1 play:)
				(deadHand setCycle: EndLoop self)
			)
			(1 (= seconds 1))
			(2
				(if register
					(messager say: 1 0 4 0 self)
				else
					(messager say: 4 5 0 0 self)
				)
			)
			(3
				(deadHand setCycle: BegLoop self)
			)
			(4
				(leftarm hide:)
				(rightarm hide:)
				(alex
					view: 691
					setLoop: 7
					cel: 0
					posn: 120 139
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(5 (alex hide:))
			(6
				(theIconBar disable:)
				(switch (rgDead stateOf690?)
					(1
						(curRoom setScript: deadInHereScript 0 24)
					)
					(else 
						(curRoom setScript: deadInHereScript 0 23)
					)
				)
			)
		)
	)
)

(instance deadInHereScript of Script
	(properties)
	
	(method (changeState newState &tmp printRet [str 200])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(theMusic number: 970 loop: 1 play:)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 98 10)
				(Message MsgGet 916 0 0 register 1 @str)
				(Display @str
					p_at 29 40
					p_width 260
					p_color 47
					p_font userFont
					p_at teJustCenter
				)
				(ego
					init:
					view: 8902
					loop: 0
					cel: 0
					normal: 0
					setScale: 0
					cycleSpeed: 25
					scaleX: 128
					scaleY: 128
					posn: 159 125
					setCycle: EndLoop self
				)
			)
			(2
				(theGame setCursor: normalCursor)
				(repeat
					(= printRet
						(KQ6Print
							window: DeathWindow
							addText: {Please select:} 62 0
							posn: 63 130
							addButton: 1 {Restore} 0 15
							addButton: 2 {Restart} 70 15
							addButton: 3 {Quit} 140 15
							init:
						)
					)
					(switch printRet
						(1
							(theGame restore:)
						)
						(2
							(theGame restart: TRUE)
						)
						(3
							(= quit TRUE)
							(break)
						)
					)
				)
			)
		)
	)
)

(instance DeathWindow of SysWindow
	(properties)
	
	(method (open)
		(= color 47)
		(= back 0)
		(super open: &rest)
	)
)

(instance issueChallenge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				(rightarm view: 691 setLoop: 3 setCycle: EndLoop self)
			)
			(1
				(messager say: 4 48 0 1 self)
			)
			(2
				(rightarm setLoop: 4 setCycle: EndLoop self)
				(ego put: 15 690)
			)
			(3
				(sfx number: 683 loop: 1 play:)
				(rightarm setLoop: 1)
				(= cycles 2)
			)
			(4
				(theConv
					add: -1 4 48 0 2
					add: -1 4 48 0 3
					add: -1 4 48 0 4
					add: -1 4 48 0 5
					init: self
				)
			)
			(5
				(deadFist setCycle: CycleTo 5 1 self)
			)
			(6
				(sfx number: 681 loop: 1 play:)
				(deadFist cel: 6)
				(= cycles 2)
			)
			(7
				(theConv
					add: -1 4 48 0 6
					add: -1 4 48 0 7
					add: -1 4 48 0 8
					add: -1 4 48 0 9
					add: -1 4 48 0 10
					init: self
				)
			)
			(8
				(theConv
					add: -1 4 48 0 11
					add: -1 4 48 0 12
					add: -1 4 48 0 13
					add: -1 4 48 0 14
					add: -1 4 48 0 15
					add: -1 4 48 0 16
					add: -1 4 48 0 17
					add: -1 4 48 0 18
					add: -1 4 48 0 19
					init: self
				)
			)
			(9
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= local1 1)
				(introGhost init: setCycle: MoveCycle @local86 introGhost)
				(= seconds 20)
			)
			(10
				(theGame handsOff:)
				(= local1 0)
				(messager say: 1 0 3 0 self)
			)
			(11 (= cycles 1))
			(12
				(rgDead stateOf690: 2)
				(client setScript: deadTouch 0 1)
			)
		)
	)
)

(instance holdUpMirror of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(= narratorX (narrator x?))
				(= narratorY (narrator y?))
				(= narratorTalkWidth (narrator talkWidth?))
				(narrator x: 10 y: 10 talkWidth: 100)
				(theConv add: -1 4 13 6 1 init: self)
			)
			(2
				(theMusic fade:)
				(= local1 0)
				(leftarm hide:)
				(rightarm
					setLoop: 5
					cycleSpeed: 7
					posn: 149 148
					setCycle: EndLoop self
				)
			)
			(3
				(sfx number: 684 loop: -1 play:)
				(lookMirror init:)
				(= seconds 2)
			)
			(4
				(theConv
					add: -1 4 13 6 2
					add: -1 4 13 6 3
					add: -1 4 13 6 4
					add: -1 4 13 6 5
					add: -1 4 13 6 6
					add: -1 4 13 6 7
					add: -1 4 13 6 8
					add: -1 4 13 6 9
					add: -1 4 13 6 10
					init: self
				)
			)
			(5
				(sfx number: 685 loop: 1 play: self)
			)
			(6
				(sfx number: 741 loop: 1 play: self)
			)
			(7
				(lookMirror dispose:)
				(sfx number: 686 loop: 1 play:)
				(tear init: setCycle: EndLoop self)
			)
			(8 (= cycles 30))
			(9
				(theConv add: -1 4 13 6 11 init: self)
			)
			(10
				(rightarm setCycle: BegLoop self)
			)
			(11
				(theConv
					add: -1 4 13 6 12
					add: -1 4 13 6 13
					add: -1 4 13 6 14
					init: self
				)
			)
			(12
				(theMusic number: 688 loop: -1 play:)
				(leftarm show:)
				(rightarm setLoop: 1 posn: 145 144)
				(= seconds 2)
				(narrator
					x: narratorX
					y: narratorY
					talkWidth: narratorTalkWidth
				)
			)
			(13
				(ego put: 24 690)
				(rgDead stateOf690: 0)
				(curRoom newRoom: 680)
			)
		)
	)
)

(instance alexHand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(rgDead stateOf690: 1)
				(client setScript: deadTouch 0 0)
			)
		)
	)
)

(instance eyeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(client cel: 2)
				(= cycles 2)
			)
			(2
				(client cel: 0)
				(self init:)
			)
		)
	)
)

(instance deadHand of Actor
	(properties
		x 110
		y 121
		view 691
		loop 6
		priority 12
		signal $0010
	)
)

(instance deadFist of Actor
	(properties
		x 224
		y 107
		view 692
		cycleSpeed 1
	)
)

(instance theConv of Conversation
	(properties)
)

(instance tear of Actor
	(properties
		x 187
		y 44
		view 692
		loop 1
		cycleSpeed 10
	)
)

(instance lookMirror of Actor
	(properties
		x 177
		y 44
		view 692
		loop 3
	)
)

(instance introGhost of Actor
	(properties
		view 690
	)
	
	(method (init)
		(self ignoreActors: 1 ignoreHorizon: 1 illegalBits: 0)
		(super init: &rest)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance deadEyes of Prop
	(properties
		x 179
		y 43
		noun 4
		view 692
		loop 4
		signal $6000
	)
	
	(method (doVerb theVerb)
		(lord doVerb: theVerb &rest)
	)
)
