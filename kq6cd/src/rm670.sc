;;; Sierra Script 1.0 - (do not remove this comment)
(script# 670)
(include sci.sh)
(use Main)
(use KQ6Print)
(use KQ6Room)
(use Kq6Procs)
(use GatePanel)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm670 0
)

(local
	local0
	[local1 26] = [12 15 22 5]
	[local27 2]
)
(instance rm670 of KQ6Room
	(properties
		noun 4
		picture 670
		south 660
	)
	
	(method (init)
		(theGame handsOff:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						224
						76
						319
						76
						319
						189
						95
						189
						44
						165
						27
						143
						64
						118
						105
						113
						162
						91
						168
						86
						184
						86
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						181
						81
						163
						81
						102
						108
						71
						109
						45
						115
						26
						127
						8
						146
						10
						172
						30
						189
						0
						189
						0
						0
						319
						0
						319
						72
						215
						72
					yourself:
				)
		)
		(super init:)
		(Lock 143 670 0)
		(ego
			init:
			reset:
			posn: 49 230
			setScale: Scaler 90 80 250 50
		)
		(self setScript: enterRoomScript)
		(torch1 setCycle: Forward ignoreActors: 1 init:)
		(torch2 setCycle: Forward ignoreActors: 1 init:)
		(shimmer1 setCycle: Forward ignoreActors: 1 init:)
		(shimmer2 setCycle: Forward ignoreActors: 1 init:)
		(gate init: ignoreHorizon: 1 ignoreActors: 1)
		(path init:)
		(river init:)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((and (& temp0 $4000) (not local0)) (theGame handsOff:) (self setScript: convertGate))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(LoadMany 0 916)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if local0
				(messager say: noun theVerb 3)
				1
			else
				(messager say: noun theVerb 4)
				1
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (newRoom n)
		(if (== n 660)
			(theGame handsOff:)
			(self setScript: dontGoAlex)
		else
			(super newRoom: n &rest)
		)
	)
	
	(method (notify param1)
		(if modelessDialog (modelessDialog dispose:))
		(DrawPic (curRoom picture?))
		(ego reset:)
		(gate view: 677)
		(shimmer1 view: 670 loop: 1)
		(shimmer2 view: 670 loop: 1)
		(torch1 view: 670)
		(torch2 view: 670)
		(cast eachElementDo: #show)
		(if param1
			(script cue:)
		else
			(theGame handsOff:)
			(self setScript: killAlexScript)
		)
	)
)

(instance torch1 of Prop
	(properties
		x 170
		y 57
		noun 7
		view 670
	)
)

(instance torch2 of Prop
	(properties
		x 250
		y 54
		noun 7
		view 670
		cel 1
		priority 10
		signal $0010
	)
)

(instance shimmer1 of Prop
	(properties
		x 172
		y 176
		view 670
		loop 1
	)
	
	(method (doVerb theVerb)
		(river doVerb: theVerb &rest)
	)
)

(instance shimmer2 of Prop
	(properties
		x 252
		y 179
		view 670
		loop 1
	)
	
	(method (doVerb theVerb)
		(river doVerb: theVerb &rest)
	)
)

(instance path of Feature
	(properties
		noun 8
		onMeCheck $0002
	)
)

(instance river of Feature
	(properties
		noun 3
		onMeCheck $0004
	)
	
	(method (init)
		(walkHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(theGame handsOff:)
				(curRoom setScript: walkInWater)
			)
			(44
				(if (Btst 58)
					(messager say: noun theVerb 14)
				else
					(messager say: noun theVerb 15)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gate of Actor
	(properties
		x 216
		y 36
		noun 2
		approachX 172
		approachY 82
		view 672
		cycleSpeed 10
	)
	
	(method (doit)
		(if
			(and
				local0
				(not (curRoom script?))
				(< (ego distanceTo: self) 40)
			)
			(theGame handsOff:)
			(curRoom setScript: walkCloseToGate)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 2)
				(if local0
					(theGame handsOff:)
					(gate setScript: 0)
					(curRoom setScript: talkGate)
				else
					(messager say: noun theVerb 4)
				)
			)
			((== theVerb 5)
				(if local0
					(theGame handsOff:)
					(curRoom setScript: handGateDead)
				else
					(theGame handsOff:)
					(curRoom setScript: handGate)
				)
			)
			((== theVerb 1)
				(if local0
					(messager say: noun theVerb 3)
				else
					(messager say: noun theVerb 4)
				)
			)
			((OneOf theVerb 48 35 28 16 13)
				(if local0
					(messager say: noun theVerb 3 0)
				else
					(messager say: noun theVerb 4 0)
				)
			)
			(local0 (messager say: noun 0 3))
			(else (messager say: noun 0 4))
		)
	)
)

(instance gateMorph of Sound
	(properties
		flags $0001
	)
)

(instance enterRoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego setMotion: PolyPath (ego x?) 186 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance convertGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic pause: 1)
				(gateMorph number: 670 loop: 1 play: self)
				(gate
					view: 675
					ignoreHorizon: 1
					setLoop: 0
					cel: 0
					posn: 210 48
					setCycle: EndLoop self
				)
			)
			(1 0)
			(2
				(theMusic pause: 0)
				(= local0 1)
				(gate view: 677 setLoop: 0 cel: 0 setScript: randomMsg)
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance dontGoAlex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 4 3 8 0 self))
			(1
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 10) self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(>
						(GetDistance (ego x?) (ego y?) (gate x?) (gate y?))
						50
					)
					(ego
						setMotion: PolyPath (gate approachX?) (gate approachY?) self
					)
				else
					(Face ego gate self)
				)
			)
			(1
				(theConv
					add: -1 2 2 3 1
					add: -1 2 2 3 2
					add: -1 2 2 3 3
					add: -1 2 2 3 4
					add: -1 2 2 3 5
					add: -1 2 2 3 6
					add: -1 2 2 3 7
					add: -1 2 2 3 8
					add: -1 2 2 3 9
					add: -1 2 2 3 10
					add: -1 2 2 3 11
					add: -1 2 2 3 12
					init: self
				)
			)
			(2
				(User canInput: 1 canControl: 1)
				(cast eachElementDo: #hide)
				(Bset 49)
				(DrawPic 98)
				(= cycles 2)
			)
			(3
				(docoProtect init: @local1 4 26)
			)
			(4
				(theGame givePoints: 3)
				(theGame setCursor: waitCursor)
				(= cycles 2)
			)
			(5
				(Bclr 49)
				(messager say: 1 5 1 0 self)
			)
			(6
				(theMusic pause: 1)
				(gateMorph number: 671 loop: 1 play: self)
				(gate view: 675 setLoop: 0)
				(gate cel: (gate lastCel:) setCycle: BegLoop self)
			)
			(7 0)
			(8
				(gate view: 672 setLoop: 0 cel: 0 posn: 216 36)
				(= cycles 5)
			)
			(9
				(gate
					view: 672
					setCel: 0
					setLoop: 1
					posn: 216 36
					ignoreActors: 1
					setPri: 5
					setCycle: EndLoop self
				)
				(gateMorph number: 342 loop: 1 play: self)
			)
			(10 0)
			(11
				(theMusic pause: 0)
				(= local0 0)
				(ego
					ignoreHorizon: 1
					setPri: (+ (gate priority?) 1)
					setMotion: PolyPath 217 74 self
				)
			)
			(12
				(ego
					setPri: (- (gate priority?) 1)
					setMotion: PolyPath 248 74 self
				)
			)
			(13
				(theIconBar enable:)
				(curRoom newRoom: 680)
			)
		)
	)
)

(instance killAlexScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: waitCursor)
				(= cycles 2)
			)
			(1
				(gate setScript: 0)
				(messager say: 1 5 2 1 self)
			)
			(2 (messager say: 1 5 2 2 self))
			(3
				(ego setMotion: PolyPath 182 81 self)
			)
			(4
				(ego
					normal: 0
					view: 673
					setLoop: 0
					cel: 0
					cycleSpeed: 5
					setScale: 0
					posn: (+ (gate x?) 3) (+ (gate y?) 20)
					setCycle: CycleTo 6 1 self
				)
			)
			(5
				(ego setCycle: EndLoop self)
				(eatSound play:)
			)
			(6
				(ego dispose:)
				(= cycles 30)
			)
			(7 (messager say: 1 5 2 3 self))
			(8 (EgoDead 17))
		)
	)
)

(instance eatSound of Sound
	(properties
		number 672
	)
)

(instance handGateDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gate setScript: 0)
				(messager say: 2 5 3 1 self)
			)
			(1
				(ego setMotion: PolyPath 182 81 self)
			)
			(2 (messager say: 2 5 3 2 self))
			(3
				(ego
					normal: 0
					view: 673
					setLoop: 0
					cel: 0
					cycleSpeed: 5
					setScale: 0
					posn: (+ (gate x?) 3) (+ (gate y?) 20)
					setScale: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(4
				(ego setCycle: EndLoop self)
				(eatSound play:)
			)
			(5
				(ego dispose:)
				(= cycles 30)
			)
			(6 (messager say: 2 5 3 3 self))
			(7 (= cycles 2))
			(8 (EgoDead 16))
		)
	)
)

(instance handGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 184 82 self)
			)
			(1
				(ego
					normal: 0
					view: 673
					setLoop: 1
					cel: 0
					posn: 178 82
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(2 (messager say: 2 5 4 1 self))
			(3 (messager say: 2 5 4 2 self))
			(4
				(ego reset: 6 setScale: Scaler 90 80 250 50)
				(self setScript: convertGate self)
			)
			(5 (messager say: 2 5 4 3 self))
			(6 (messager say: 2 5 4 4 self))
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance randomMsg of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(switch (= temp0 (Random 1 3))
					(1
						(messager say: 6 0 10 0 self)
					)
					(2
						(messager say: 6 0 11 0 self)
					)
					(3
						(messager say: 6 0 12 0 self)
					)
				)
			)
			(2 (self init:))
		)
	)
)

(instance walkCloseToGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gate setScript: 0)
				(= local0 0)
				(messager say: 4 3 13 1 self)
			)
			(1
				(messager say: 4 3 13 2 self)
			)
			(2
				(ego
					normal: 0
					view: 673
					setLoop: 0
					cel: 0
					cycleSpeed: 5
					posn: (+ (gate x?) 3) (+ (gate y?) 20)
					setCycle: CycleTo 6 1 self
				)
			)
			(3
				(ego setCycle: EndLoop self)
				(eatSound play:)
			)
			(4
				(ego dispose:)
				(= cycles 30)
			)
			(5
				(messager say: 4 3 13 3 self)
			)
			(6 (= cycles 2))
			(7 (EgoDead 16))
		)
	)
)

(instance splashSound of Sound
	(properties
		number 923
	)
)

(instance walkInWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gate setScript: 0)
				(ego setMotion: PolyPath 138 103 self)
			)
			(1
				(Load rsVIEW 674)
				(= cycles 2)
			)
			(2
				(theMusic number: 653 loop: 1 play:)
				(ego
					normal: 0
					view: 674
					setLoop: 0
					cel: 0
					cycleSpeed: 3
					setCycle: CycleTo 8 1 self
				)
			)
			(3
				(splashSound play:)
				(ego setCycle: EndLoop self)
			)
			(4 (ego dispose:) (= cycles 2))
			(5
				(if local0
					(messager say: 3 3 3 1 self)
				else
					(messager say: 3 3 4 1 self)
				)
			)
			(6
				(if local0
					(messager say: 3 3 3 2 self)
				else
					(messager say: 3 3 4 2 self)
					(= cycles 1)
				)
			)
			(7
				(if local0
					(messager say: 3 3 3 3 self)
				else
					(= cycles 1)
				)
			)
			(8 (EgoDead 31))
		)
	)
)

(instance docoProtect of GatePanel
	(properties
		x 84
		y 17
		noun 1
		view 678
		offsetX 30
		offsetY 20
		maxCol 5
		maxRow 5
		numButtons 30
	)
	
	(method (init)
		(gate setScript: 0)
		(ego view: 2002)
		(gate view: 2002)
		(torch1 view: 2002)
		(torch2 view: 2002)
		(shimmer1 view: 2002)
		(shimmer2 view: 2002)
		(KQ6Print
			modeless: 1
			width: 290
			posn: 10 146
			say: 1 0 0 0 1 0 0 671
			init:
		)
		(super init: &rest)
	)
	
	(method (drawButton &tmp temp0)
		(= temp0 0)
		(if (not (OneOf value 26 27 29 30))
			(if
				(not
					(& [local27 (/ value 16)] (>> $8000 (mod value 16)))
				)
				(= [local27 (/ value 16)]
					(| [local27 (/ value 16)] (>> $8000 (mod value 16)))
				)
				(= temp0 (super drawButton: &rest))
				(soundFx number: 910 setLoop: 1 play:)
			else
				(= temp0 0)
			)
		)
		(return temp0)
	)
)

(instance theConv of Conversation
	(properties)
)
