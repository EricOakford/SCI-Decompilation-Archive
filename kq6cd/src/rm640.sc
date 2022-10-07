;;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
(include sci.sh)
(use Main)
(use KQ6Print)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	rm640 0
)

(local
	[local0 4] = [0 -3 12 11]
	[local4 4] = [0 8 9 9]
	[local8 37] = [2 0 124 93 2 1 128 74 2 2 143 61 2 3 152 60 2 4 153 75 2 5 154 94 2 6 159 121 2 7 161 149 2 8 159 167 -32768]
	[local45 17] = [2 9 166 154 2 10 178 153 2 11 182 156 2 12 185 167 -32768]
	[local62 41] = [0 0 194 135 0 1 197 135 0 2 214 120 0 3 226 116 0 4 226 110 0 5 238 95 0 6 252 87 0 7 239 108 0 8 205 130 0 9 205 130 -32768]
)
(instance rm640 of KQ6Room
	(properties
		noun 3
		picture 640
		horizon 0
		north 650
		south 630
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						-10
						319
						-10
						319
						189
						227
						189
						191
						160
						181
						142
						149
						120
						156
						108
						140
						98
						148
						91
						142
						86
						142
						42
						135
						42
						135
						87
						119
						95
						119
						99
						135
						107
						121
						117
						122
						126
						132
						139
						142
						150
						142
						175
						27
						175
						49
						189
					yourself:
				)
		)
		(super init: &rest)
		(Lock 143 modNum 0)
		(theIconBar enable:)
		(theGame handsOff:)
		(ego init: reset: 3 setScale: Scaler 100 70 200 70)
		(doorMaster
			init:
			signal: (| (doorMaster signal?) $1000)
			approachVerbs: 2 0
		)
		(keyMaster
			init:
			signal: (| (keyMaster signal?) $1000)
			approachVerbs: 2
		)
		(cond 
			((ego has: 44) 0)
			((Btst 115)
				(boneKey
					init:
					view: 647
					setLoop: 0
					cel: 1
					posn: (+ (keyMaster x?) 78) (+ (keyMaster y?) 1)
					noun: 7
					ignoreActors: 1
				)
			)
			(else
				(boneKey
					init:
					posn: (+ (keyMaster x?) 16) (- (keyMaster y?) 14)
					ignoreActors: 1
				)
			)
		)
		(skelLeft init:)
		(skelRight init:)
		(boneMallet init:)
		(ghost init: setScript: ghostScr)
		(door init:)
		(theSkull init:)
		(xylophone init:)
		(if (Btst 44)
			(theMusic stop:)
			(theGlobalSound stop:)
			(soundFx stop:)
			(soundFx2 stop:)
			(self setScript: deathCartoonScr)
		else
			(ego setScript: musicStuffScript)
			(lineGhost init: setScript: ghostLineScr)
			(switch prevRoomNum
				(650 (ego posn: 133 97))
				(else  (ego posn: 109 187))
			)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((== temp0 16384) (theGame handsOff:) (self setScript: goNoFarther))
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(LoadMany 0 969 942)
	)
)

(instance musicStuffScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 640 loop: 1 play: self)
			)
			(1
				(theMusic stop:)
				(theMusic number: 600 loop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance goNoFarther of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doorMaster
					view: 6407
					loop: 0
					cel: 0
					posn: (+ (doorMaster x?) 6) (+ (doorMaster y?) 19)
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 30))
			(2 (ego setHeading: 45 self))
			(3 (= ticks 15))
			(4 (messager say: 8 3 0 2 self))
			(5
				(if (> (ego x?) 129)
					(ego
						setLoop: 6
						setCycle: Reverse
						setMotion: MoveTo (- (ego x?) 4) (+ (ego y?) 7) self
					)
				else
					(ego
						setLoop: 3
						setCycle: Reverse
						setMotion: MoveTo (ego x?) (+ (ego y?) 7) self
					)
				)
			)
			(6
				(ego setCycle: 0 setLoop: -1)
				(doorMaster cel: 5 setCycle: BegLoop self)
			)
			(7
				(doorMaster
					view: 642
					loop: 0
					cel: 0
					posn: (- (doorMaster x?) 6) (- (doorMaster y?) 19)
				)
				(= cycles 1)
			)
			(8
				(ego reset: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance deathCartoonScr of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((or (ego has: 44) (Btst 115)) 0)
			((== (keyMaster view?) 6404)
				(boneKey
					posn: (+ (keyMaster x?) 14) (- (keyMaster y?) 14)
				)
			)
			((== (keyMaster loop?) 1)
				(switch (keyMaster cel?)
					(7
						(boneKey
							posn: (+ (keyMaster x?) 9) (- (keyMaster y?) 16)
						)
					)
					(9
						(boneKey
							posn: (+ (keyMaster x?) 9) (- (keyMaster y?) 18)
						)
					)
					(10
						(boneKey
							posn: (+ (keyMaster x?) 16) (- (keyMaster y?) 14)
						)
					)
				)
			)
			((== (keyMaster loop?) 2)
				(boneKey
					posn: (+ (keyMaster x?) 13) (- (keyMaster y?) 16)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2) (ego hide:))
			(1
				(theMusic loop: 1 number: 975 flags: 1 play:)
				(keyMaster view: 643 loop: 1 cel: 0 setCycle: EndLoop self)
				(ego
					posn: 146 174
					show:
					view: 6402
					loop: 0
					cel: 0
					normal: 0
					cycleSpeed: 15
					setCycle: CycleTo 4 1 self
				)
			)
			(2 0)
			(3
				(keyMaster loop: 1 cel: 0)
				(ego setCycle: EndLoop self)
			)
			(4
				(keyMaster
					view: 6404
					loop: 0
					cel: 0
					posn: (keyMaster x?) (- (keyMaster y?) 3)
				)
				(ego posn: 142 133 loop: 1 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(5
				(self setScript: modeLessScript)
				(doorMaster
					view: 6406
					loop: 0
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(6
				(doorMaster setCycle: EndLoop)
				(ego setCycle: EndLoop self)
			)
			(7
				(doorMaster loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(modeLessScript cue:)
				(doorMaster view: 6406 loop: 0 cel: 0)
				(ego loop: 2 cel: 0 cycleSpeed: 10 setCycle: EndLoop self)
			)
			(9 (door setCycle: EndLoop self))
			(10
				(ego dispose:)
				(door setCycle: BegLoop self)
			)
			(11
				(cast eachElementDo: #addToPic)
				(= seconds 3)
			)
			(12
				(if modelessDialog (modelessDialog dispose:))
				(theGame setCursor: normalCursor)
				(repeat
					(switch
						(KQ6Print
							posn: -1 10
							addIcon: 649 0 0 67 1
							say: 1 0 0 deathReason 1 0 40 916
							addButton: 1 {Restore} 0 78
							addButton: 2 {Restart} 67 78
							addButton: 3 {Quit} 134 78
							init:
						)
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

(instance modeLessScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(KQ6Print
					font: userFont
					posn: 200 100
					say: 0 1 0 1 1
					modeless: 1
					init:
				)
				(= seconds 4)
			)
			(1
				(if modelessDialog (modelessDialog dispose:))
			)
			(2
				(KQ6Print
					font: userFont
					posn: 200 100
					say: 0 1 0 1 2
					modeless: 1
					init:
				)
				(= seconds 3)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
			)
		)
	)
)

(instance egoGiveTicketScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame givePoints: 3)
				(ego put: 28 640)
				(ego setMotion: PolyPath 145 133 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 5 49 0 1 self)
			)
			(3 (ego setHeading: 0 self))
			(4
				(self setScript: takeTicketScr self)
			)
			(5 (takeTicketScr cue:))
			(6
				(messager say: 5 49 0 2 self)
			)
			(7 (ego reset: 0) (= cycles 1))
			(8
				(ego setMotion: PolyPath 139 90 self)
			)
			(9 (door setCycle: EndLoop self))
			(10
				(ego dispose:)
				(door setCycle: BegLoop self)
			)
			(11
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance takeTicketScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= client egoGiveTicketScr)
					(messager say: 1 0 2 2 self)
				else
					(= cycles 1)
				)
			)
			(1
				(doorMaster
					view: 6406
					loop: 0
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2 (client cue:))
			(3
				(doorMaster setCycle: EndLoop self)
			)
			(4
				(doorMaster loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(doorMaster view: 642 loop: 0 cel: 0)
				(= cycles 2)
			)
			(6
				(if (!= client egoGiveTicketScr)
					(messager say: 1 0 2 3 self)
				else
					(= cycles 1)
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance ghostLineScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(client cel: 0 loop: 0 posn: 141 167 setCycle: EndLoop self)
			)
			(2
				(client cel: 0 loop: 1 setCycle: Forward)
				(keyMaster view: 643 loop: 1 cel: 0 setCycle: CycleTo 9 1 self)
			)
			(3
				(client cel: 0 loop: 2 setCycle: EndLoop self)
				(keyMaster setCycle: EndLoop self)
			)
			(4 0)
			(5
				(keyMaster loop: 1 cel: 0)
				(= cycles 2)
			)
			(6 (messager say: 1 0 2 1 self))
			(7
				(client cel: 0 loop: 3 y: 136 setCycle: Forward)
				(= ticks 30)
			)
			(8
				(self setScript: takeTicketScr self)
			)
			(9 (script cue:))
			(10
				(client cel: 0 loop: 4 setCycle: EndLoop self)
			)
			(11 (door setCycle: EndLoop self))
			(12
				(client hide:)
				(door setCycle: BegLoop self)
			)
			(13
				(keyMaster view: 6404 loop: 0 cel: 0)
				(client dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance playXylophone of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 6) (== (theMusic prevSignal?) -1))
			(ego setCycle: 0)
			(self cue:)
		)
		(if
		(and (== state 12) (not (chorusRight script?)))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 200 185 self)
			)
			(1
				(ego
					normal: 0
					view: 641
					posn: (- (boneMallet x?) 3) (+ (boneMallet y?) 9)
					setLoop: 0
					cycleSpeed: 10
					cel: 0
					setCycle: EndLoop self
				)
				(boneMallet hide:)
			)
			(2
				(UnLoad 128 900)
				(if (Btst 100)
					(messager say: 6 5 4 1 self)
				else
					(messager say: 6 5 3 1 self)
				)
			)
			(3
				(if (Btst 100)
					(= cycles 1)
				else
					(theGame givePoints: 2)
					(messager say: 6 5 3 2 self)
					(Bset 100)
				)
				(if (ego script?) (ego setScript: 0))
				(theMusic stop:)
			)
			(4
				(ego
					posn: (+ (boneMallet x?) [local0 1]) (+ (boneMallet y?) [local4 1])
					setLoop: 1
					setCycle: EndLoop self
				)
			)
			(5
				(theMusic number: 641 loop: 1 play:)
				(theMusic prevSignal: 0)
				(keyMaster setScript: keyDanceScript)
				(= register 2)
				(= cycles 1)
			)
			(6
				(ego
					posn:
						(+ (boneMallet x?) [local0 register])
						(+ (boneMallet y?) [local4 register])
					setLoop: register
					setCycle: EndLoop self
				)
			)
			(7
				(if (== (theMusic prevSignal?) -1)
					(keyDanceScript cue:)
					(theMusic number: 600 loop: -1 play:)
					(= cycles 1)
				else
					(= register (Random 2 3))
					(self start: 6 init:)
				)
			)
			(8
				(ego
					posn: (+ (boneMallet x?) [local0 1]) (+ (boneMallet y?) [local4 1])
					setLoop: 1
				)
				(ego cel: (ego lastCel:) setCycle: BegLoop self)
			)
			(9
				(ego setLoop: 0 cel: 5 setCycle: BegLoop self)
			)
			(10
				(boneMallet show:)
				(ego reset: 0 posn: 198 181)
				(= cycles 1)
			)
			(11
				(messager say: 6 5 3 4 self)
			)
			(12 0)
			(13
				(doorMaster view: 642 setLoop: 0 cel: 0 setCycle: 0)
				(theGame handsOn:)
				(self start: 0 dispose:)
			)
		)
	)
)

(instance keyDanceScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(cast contains: chorusRight)
				(not (chorusRight script?))
				(== (theMusic prevSignal?) 10)
			)
			(theMusic prevSignal: 1)
			(chorusRight setScript: chorusScript 0 0)
			((skelLeft script?) cue:)
			((skelRight script?) cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(= seconds 3)
			)
			(1
				(theMusic pause:)
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 6 5 3 3
					init: self
				)
			)
			(2
				(theMusic pause: 0)
				(skelLeft setScript: skelLeftScript)
				(skelRight setScript: skelRightScript)
				(doorMaster view: 646 setLoop: 0 cel: 0)
				(chorusRight init:)
				(= seconds 10)
			)
			(3
				(doorMaster setScript: doorScript)
				(= seconds 10)
			)
			(4
				(keyMaster view: 645 setLoop: 0 cel: 0 setCycle: Forward)
				(if (and (not (ego has: 44)) (not (Btst 115)))
					(boneKey view: 645 setLoop: 1 cel: 1)
					(= register 1)
				)
				(= seconds 20)
			)
			(5
				(= register 0)
				(if (and (not (ego has: 44)) (not (Btst 115)))
					(boneKey
						setLoop: 2
						cel: 0
						posn: (keyMaster x?) (keyMaster y?)
						cycleSpeed: 0
						setCycle: MoveCycle @local8 self
					)
					(theGlobalSound number: 825 loop: 1)
				else
					(= cycles 1)
				)
			)
			(6
				(if (and (not (ego has: 44)) (not (Btst 115)))
					(theGlobalSound play:)
					(boneKey setCycle: MoveCycle @local45 self)
				else
					(= cycles 1)
				)
			)
			(7
				(if (and (not (ego has: 44)) (not (Btst 115)))
					(theGlobalSound play:)
					(Bset 115)
					(boneKey view: 647 loop: 0 cel: 1 noun: 7)
				)
				((doorMaster script?) cue:)
			)
			(8
				(keyMaster view: 6404 setLoop: 0 cel: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance skelLeftScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(skelLeft view: 6403 setLoop: 1 cel: 0 setCycle: Forward)
			)
			(1
				(skelLeft view: 644 setLoop: 0 cel: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance skelRightScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(skelRight view: 6403 setLoop: 0 cel: 0 setCycle: Forward)
			)
			(1
				(skelRight view: 644 setLoop: 0 cel: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance doorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (doorMaster setCycle: Forward))
			(1
				(doorMaster setLoop: 0 cel: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance chorusScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chorusRight setCycle: Forward setMotion: MoveTo 160 184 self)
				(chorusMid init:)
				(chorusLeft init:)
			)
			(1
				(chorusRight setCycle: Forward setMotion: MoveTo -18 184 self)
			)
			(2
				(if (not register)
					(chorusMid dispose:)
					(chorusLeft dispose:)
					(chorusRight dispose:)
					(self dispose:)
				else
					(-- register)
					(self init:)
				)
			)
		)
	)
)

(instance getKeyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (- (boneKey x?) 41) (boneKey y?) self
				)
			)
			(1
				(ego
					normal: 0
					view: 6405
					setLoop: 3
					cel: 0
					posn: (- (boneKey x?) 20) (+ (boneKey y?) 5)
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(boneKey dispose:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego
					reset: 0
					posn: (- (ego x?) 21) (- (ego y?) 5)
					get: 44
				)
				(theGame givePoints: 1)
				(Bclr 115)
				(= cycles 1)
			)
			(4 (messager say: 7 5 0 1 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ghostScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: MoveCycle @local62 self)
			)
			(1
				(if (== (curRoom script?) playXylophone)
					(client dispose:)
					(self dispose:)
				else
					(= seconds (Random 5 10))
				)
			)
			(2 (self init:))
		)
	)
)

(instance chorusRight of Actor
	(properties
		x -18
		y 184
		view 648
		loop 2
		signal $4800
	)
)

(instance chorusMid of Actor
	(properties
		x -18
		y 184
		view 648
		signal $4800
	)
	
	(method (doit)
		(self
			cel: (chorusRight cel?)
			x: (- (chorusRight x?) 25)
			y: (chorusRight y?)
		)
		(super doit:)
	)
)

(instance chorusLeft of Actor
	(properties
		x -18
		y 184
		view 648
		loop 1
		signal $4800
	)
	
	(method (doit)
		(self
			cel: (chorusRight cel?)
			x: (- (chorusRight x?) 50)
			y: (chorusRight y?)
		)
		(super doit:)
	)
)

(instance boneMallet of View
	(properties
		x 221
		y 177
		noun 6
		view 647
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(curRoom setScript: playXylophone)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance skelLeft of Prop
	(properties
		x 85
		y 144
		noun 9
		view 644
		loop 1
	)
)

(instance skelRight of Prop
	(properties
		x 192
		y 132
		noun 9
		view 644
	)
)

(instance doorMaster of Prop
	(properties
		x 170
		y 118
		noun 5
		approachX 141
		approachY 134
		view 642
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager say: noun theVerb 0 0)
			)
			(1
				(messager say: noun theVerb 0 0)
			)
			(2
				(messager say: noun theVerb 0 0)
			)
			(50
				(messager say: noun theVerb 0 1)
			)
			(49
				(theGame handsOff:)
				(curRoom setScript: egoGiveTicketScr)
			)
			(35 (messager say: 5 35 0 0))
			(else  (messager say: 5 0 0 0))
		)
	)
)

(instance boneKey of Prop
	(properties
		noun 11
		view 647
		cel 1
	)
	
	(method (doit)
		(if (keyDanceScript register?)
			(self
				cel: (keyMaster cel?)
				x: (+ (keyMaster x?) 4)
				y: (- (keyMaster y?) 53)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 115)
					(theGame handsOff:)
					(curRoom setScript: getKeyScript)
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

(instance keyMaster of Prop
	(properties
		x 101
		y 164
		noun 4
		approachX 109
		approachY 176
		view 6404
	)
)

(instance ghost of Actor
	(properties
		x 226
		y 96
		view 640
		priority 13
		signal $0010
	)
)

(instance lineGhost of Prop
	(properties
		x 133
		y 183
		view 6401
		loop 5
	)
)

(instance door of Prop
	(properties
		x 141
		y 32
		approachX 140
		approachY 90
		view 647
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 3)
			(messager say: 8 3 0 1)
			(ego setMotion: PolyPath approachX approachY)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theSkull of Feature
	(properties
		noun 10
		onMeCheck $0008
	)
)

(instance xylophone of Feature
	(properties
		noun 6
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(curRoom setScript: playXylophone)
		else
			(super doVerb: theVerb &rest)
		)
	)
)
