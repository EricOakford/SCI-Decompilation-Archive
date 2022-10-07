;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include sci.sh)
(use Main)
(use rgDead)
(use KQ6Room)
(use Inset)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	[local0 16] = [158 52 0 299 58 1 113 82 2 65 87 3 49 87 4]
	local16
	local17
	local18
	local19
)
(instance rm650 of KQ6Room
	(properties
		noun 3
		picture 650
		horizon 88
		north 660
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						105
						189
						105
						178
						253
						178
						267
						151
						267
						112
						253
						109
						199
						108
						123
						108
						77
						112
						52
						106
						52
						0
						319
						0
						319
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						0
						168
						0
						0
						43
						0
						43
						104
						57
						112
						87
						115
						122
						110
						210
						110
						251
						113
						265
						117
						265
						137
						152
						168
					yourself:
				)
		)
		(super init: &rest)
		(switch prevRoomNum
			(660
				(ego posn: 79 113)
				(= local18 1)
			)
			(else 
				(theMusic number: 650 loop: -1 play:)
				(ego posn: 47 172)
			)
		)
		(proc70_1 flames @local0)
		(glow1 init: setCycle: RandCycle)
		(glow2 init: setCycle: RandCycle)
		(riverStyx init:)
		(fallFeat init:)
		(knight init:)
		(ego
			init:
			baseSetter: bSetter
			observeControl: -32768
			setScale: Scaler 100 65 150 100
		)
		(path init:)
		(theGame handsOn:)
		(ghost init: hide: setScript: ghostScript)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((>= (ego y?) 186) (theGame handsOff:) (self setScript: cantLeaveSouth))
			((<= (ego x?) 5) (theGame handsOff:) (self setScript: cantLeaveWest))
			((& temp0 $0800)
				(if
					(and
						(not (ego isStopped:))
						(or
							(< ((ego mover?) y?) (ego y?))
							(OneOf (ego loop?) 3 6 7)
						)
					)
					(self setScript: egoNorthOverKnightScr)
				)
			)
			((& temp0 $0002)
				(if
					(and
						local18
						(not (ego isStopped:))
						(or
							(> ((ego mover?) y?) (ego y?))
							(OneOf (ego loop?) 2 4 5)
						)
					)
					(self setScript: egoSouthOverKnightScr)
				)
			)
			((& temp0 $1000) (self newRoom: north))
			(
				(not
					(if (or (& temp0 $4000) (& temp0 $2000))
					else
						(& temp0 $0002)
					)
				)
				(theGame handsOff:)
				(self setScript: egoFallScr)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 991)
	)
)

(instance egoNorthOverKnightScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local16 ((ego mover?) finalX?))
					(= local17 ((ego mover?) finalY?))
				)
				(theGame handsOff:)
				(ego
					normal: 0
					view: 652
					loop: 2
					cel: 3
					posn: (+ (ego x?) 1) (+ (ego y?) 5)
					setPri: 9
				)
				(= cycles 1)
			)
			(1 (ego setCycle: CycleTo 7 1 self))
			(2
				(ego setPri: 8 x: (+ (ego x?) 2) setCycle: EndLoop self)
			)
			(3
				(ego posn: (- (ego x?) 2) (- (ego y?) 11) reset: 3)
				(= cycles 2)
			)
			(4
				(if
					(and
						local16
						(> (GetDistance local16 local17 (ego x?) (ego y?)) 20)
					)
					(ego setMotion: PolyPath local16 local17)
					(= local16 0)
				)
				(theGame handsOn:)
				(= local18 1)
				(self dispose:)
			)
		)
	)
)

(instance egoSouthOverKnightScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local16 ((ego mover?) finalX?))
					(= local17 ((ego mover?) finalY?))
				)
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: 2
					setCycle: 0
					setPri: 9
					setMotion: JumpTo 261 137 self
				)
			)
			(2
				(ego reset:)
				(= local18 0)
				(if
					(and
						local16
						(> (GetDistance local16 local17 (ego x?) (ego y?)) 20)
					)
					(ego setMotion: PolyPath local16 local17)
					(= local16 0)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoFallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego y?) 124)
					(= register 1)
					(ego setMotion: PolyPath 163 108 self)
				else
					(= register 0)
					(ego setMotion: PolyPath 119 168 self)
				)
			)
			(1 (= cycles 2))
			(2 (messager say: 3 3 3 1 self))
			(3
				(theMusic stop:)
				(theMusic number: 653 loop: 1 play:)
				(if register
					(ego
						normal: 0
						view: 653
						setLoop: 0
						cel: 0
						cycleSpeed: 5
						setCycle: EndLoop self
					)
				else
					(ego
						normal: 0
						view: 653
						setLoop: 1
						cel: 0
						setPri: 8
						cycleSpeed: 5
						setCycle: EndLoop self
					)
				)
			)
			(4
				(ego dispose:)
				(splashSound play: self)
			)
			(5 (messager say: 3 3 3 2 self))
			(6 (EgoDead 31))
		)
	)
)

(instance splashSound of Sound
	(properties
		number 923
	)
)

(instance flames of Prop
	(properties
		noun 12
		view 650
		priority 3
		signal $4010
	)
)

(instance glow1 of Prop
	(properties
		x 299
		y 51
		view 650
		loop 6
		priority 2
		signal $4010
	)
)

(instance glow2 of Prop
	(properties
		x 65
		y 84
		view 650
		loop 7
		priority 2
		signal $4010
	)
)

(instance knightInset of Inset
	(properties
		view 652
		x 228
		y 112
		disposeNotOnMe 1
		noun 8
	)
	
	(method (init)
		(theIconBar disable: 6)
		(knightInsetFeat init:)
		(knightFeat init:)
		(ribbonFeat init:)
		(handFeat init:)
		(super init: &rest)
		(if (== ((inventory at: 15) owner?) curRoomNum)
			(gauntlet init:)
		)
	)
	
	(method (dispose)
		(ribbonFeat dispose:)
		(knightFeat dispose:)
		(knightInsetFeat dispose:)
		(handFeat dispose:)
		(super dispose:)
		(theIconBar enable: 6)
	)
	
	(method (handleEvent event)
		(cond 
			((ribbonFeat onMe: event) (ribbonFeat handleEvent: event))
			((handFeat onMe: event) (handFeat handleEvent: event))
			((knightFeat onMe: event) (knightFeat handleEvent: event))
			((knightInsetFeat onMe: event) (knightInsetFeat handleEvent: event))
			(else (super handleEvent: event))
		)
	)
)

(instance getGauntletScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(knightInset dispose:)
				(ego reset: 0)
				(= seconds 2)
			)
			(1
				(ego setMotion: PolyPath 267 130 self)
			)
			(2
				(ego
					normal: 0
					view: 652
					loop: 1
					cycleSpeed: 6
					cel: 0
					posn: 277 131
					setCycle: EndLoop self
				)
				(scrape play:)
			)
			(3
				(ego reset: 0 posn: 267 130 get: 15)
				(= cycles 2)
			)
			(4
				(theGame givePoints: 1)
				(messager say: 6 5 0 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance scrape of Sound
	(properties
		number 652
	)
)

(instance gauntlet of Prop
	(properties
		x 198
		y 120
		noun 6
		view 652
		cel 1
		priority 15
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getGauntletScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance knightGhost of Prop
	(properties
		x 249
		y 145
		view 651
		priority 9
		signal $0010
	)
)

(instance ghost of Prop
	(properties
		x 95
		y 112
		noun 10
		view 650
		loop 5
		priority 8
		signal $0010
		cycleSpeed 7
	)
)

(instance ghostScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 90 300)))
			(1
				(ghost show: setCycle: EndLoop self)
			)
			(2 (ghost hide:) (= cycles 1))
			(3 (self init:))
		)
	)
)

(instance ghostTormentScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== client curRoom)
					(ego setMotion: 0 setHeading: 45)
				)
				(theMusic number: 651 loop: 1 play:)
				((ScriptID 70 0) setIt: 4)
				(knightGhost init: cycleSpeed: 10 setCycle: EndLoop self)
			)
			(1
				(ego hide:)
				(knightGhost loop: 1 cel: 0 setPri: -1 setCycle: EndLoop self)
			)
			(2
				(ego show:)
				(knightGhost
					loop: 2
					cel: 0
					posn: 201 127
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(3
				(theMusic stop:)
				(theMusic number: 650 loop: -1 play:)
				(knightGhost dispose:)
				(= seconds 2)
			)
			(4 (messager say: 1 0 4 1 self))
			(5
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance knightInsetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (knight approachX?) (knight approachY?) self
				)
			)
			(1
				(if (not ((ScriptID 70 0) isSet: 4))
					(self setScript: ghostTormentScr self)
				else
					(= cycles 1)
				)
			)
			(2 (ego setHeading: 90 self))
			(3 (= cycles 2))
			(4
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5)
				(knightInset init: self curRoom)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance knight of Feature
	(properties
		x 300
		y 128
		noun 4
		sightAngle 40
		onMeCheck $0002
		approachX 251
		approachY 147
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 1)
			(theGame handsOff:)
			(curRoom setScript: knightInsetScr)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance fallFeat of Feature
	(properties
		onMeCheck $0080
	)
	
	(method (init)
		(super init:)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 3)
			(theGame handsOff:)
			(curRoom setScript: egoFallScr)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance riverStyx of Feature
	(properties
		noun 9
		onMeCheck $0004
	)
	
	(method (init)
		(super init:)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 3)
			(theGame handsOff:)
			(curRoom setScript: egoFallScr)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance bSetter of Code
	(properties)
	
	(method (doit param1)
		(param1 brTop: (param1 y?))
		(param1 brBottom: (param1 y?))
		(param1 brLeft: (param1 x?))
		(param1 brRight: (param1 x?))
	)
)

(instance path of Feature
	(properties
		noun 13
		onMeCheck $4000
	)
)

(instance cantLeaveSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 3 3 2 0 self))
			(1
				(ego
					setMotion: MoveTo (+ (ego x?) 3) (- (ego y?) 10) self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cantLeaveWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 3 3 2 0 self))
			(1
				(ego
					setMotion: MoveTo (+ (ego x?) 10) (- (ego y?) 3) self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance knightInsetFeat of Feature
	(properties
		noun 8
		nsTop 62
		nsLeft 154
		nsBottom 155
		nsRight 307
	)
)

(instance ribbonFeat of Feature
	(properties
		noun 7
		nsTop 99
		nsLeft 232
		nsBottom 107
		nsRight 248
	)
)

(instance handFeat of Feature
	(properties
		noun 11
		nsTop 114
		nsLeft 187
		nsBottom 130
		nsRight 212
	)
)

(instance knightFeat of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(= onMeCheck
			(= local19
				((Polygon new:)
					type: 0
					init:
						298
						68
						298
						81
						290
						91
						303
						103
						304
						118
						282
						151
						267
						153
						280
						124
						265
						129
						256
						152
						234
						152
						237
						142
						218
						133
						157
						141
						156
						127
						230
						110
						253
						86
						269
						89
						275
						68
					yourself:
				)
			)
		)
		(super init: &rest)
	)
)
