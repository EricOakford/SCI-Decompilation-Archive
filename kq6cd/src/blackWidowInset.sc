;;; Sierra Script 1.0 - (do not remove this comment)
(script# 461)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	blackWidowInset 0
)

(local
	local0
	theMouseX
	theMouseY
)
(instance myConv of Conversation
	(properties)
)

(instance blackWidowInset of KQ6Room
	(properties
		modNum 460
		picture 465
	)
	
	(method (init)
		(super init: &rest)
		(theIconBar disable: 6)
		(theMusic fade: 0 10 10)
		(theGlobalSound
			number: 465
			setLoop: -1
			setVol: 0
			play:
			fade: 127 10 10
		)
		(features add: roomAtLarge web eachElementDo: #init)
		(if (not (Btst 160)) (looseThread init:))
		(if (not (Btst 136)) (parchment init:))
		(spider init:)
		(self setScript: helloScript)
	)
	
	(method (dispose)
		(Bclr 59)
		(super dispose:)
	)
	
	(method (newRoom)
		(theIconBar enable: 6)
		(super newRoom: &rest)
	)
)

(instance looseThread of Prop
	(properties
		x 190
		y 169
		noun 13
		modNum 460
		sightAngle 180
		view 465
		loop 3
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not (Btst 160))
					(theGame handsOff:)
					(curRoom setScript: unravelWeb)
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

(instance parchment of Prop
	(properties
		x 104
		y 93
		noun 12
		modNum 460
		sightAngle 180
		view 465
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if local0
					(theGame handsOff:)
					((ScriptID 40 0) gotParchment: 1)
					(curRoom newRoom: 460)
				else
					(curRoom setScript: bitParchment)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance love of Actor
	(properties
		x 146
		y 97
		noun 12
		modNum 460
		yStep 15
		view 465
		loop 4
		priority 15
		signal $4810
		xStep 20
	)
)

(instance spider of Actor
	(properties
		x 196
		y 137
		noun 11
		modNum 460
		sightAngle 180
		yStep 10
		view 466
		loop 1
		illegalBits $0000
		xStep 14
	)
	
	(method (init)
		(self sightAngle: 180 setCycle: Forward)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: touchSpider)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomAtLarge of Feature
	(properties
		modNum 460
		onMeCheck $0020
	)
	
	(method (doVerb)
		(theGame handsOn:)
		(curRoom newRoom: 460)
	)
)

(instance web of Feature
	(properties
		noun 9
		modNum 460
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 9 1 0 1 0 460)
			)
			(5
				(= theMouseX mouseX)
				(if (> (= theMouseY mouseY) 150) (= theMouseY 150))
				(curRoom setScript: spiderRush)
			)
			(2
				(messager say: 9 2 0 1 0 460)
			)
			(else 
				(messager say: 9 0 0 1 0 460)
			)
		)
	)
)

(instance spiderRush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 9 5 0 1 self 460)
			)
			(1
				((ScriptID 40 0) spiderBit: 1)
				(theGame handsOn:)
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance bitParchment of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 12 5 11 1 self 460)
			)
			(1
				((ScriptID 40 0) parchmentBit: 1)
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance touchSpider of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 11 5 0 1 self 460)
			)
			(1
				((ScriptID 40 0) spiderBit: 1)
				(theGame handsOn:)
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance unravelWeb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame givePoints: 1)
				(Bset 160)
				(= local0 1)
				(messager say: 13 5 0 1 self 460)
			)
			(1
				(soundFx2 number: 467 setLoop: 1 play:)
				(looseThread setCycle: EndLoop self)
			)
			(2
				(theGame handsOn:)
				(spider setLoop: 2 setMotion: MoveTo 196 137 self)
				(= seconds 2)
			)
			(3 (looseThread dispose:))
			(4
				(spider setCycle: 0)
				(theIconBar disable: 0)
				(= seconds 5)
			)
			(5
				(spider
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 167 76 self
				)
			)
			(6
				(messager say: 13 5 0 3 self 460)
				(= local0 0)
				(self dispose:)
			)
		)
	)
)

(instance helloScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(spider
					setCycle: Walk
					setLoop: 1
					ignoreActors:
					ignoreHorizon:
					setMotion: MoveTo 167 76 self
				)
			)
			(1
				(if (Btst 56)
					(messager say: 8 1 10 1 self 460)
				else
					(myConv
						add: 460 8 1 9 1
						add: 460 8 1 9 2
						add: 460 8 1 9 3
						add: 460 8 1 9 4
						init: self
					)
				)
			)
			(2
				(if (Btst 56)
					(self cue:)
				else
					(myConv
						add: 460 8 1 9 5
						add: 460 8 1 9 6
						add: 460 8 1 9 7
						init: self
					)
				)
			)
			(3
				(Bset 56)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)
