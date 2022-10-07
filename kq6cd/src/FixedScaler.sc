;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Conv)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm630 0
)

(local
	[local0 73] = [1 0 183 156 1 1 184 152 1 2 185 141 1 3 184 128 1 4 183 113 1 5 182 102 1 6 182 88 1 7 183 83 1 8 184 78 1 9 184 77 1 10 183 78 1 11 183 78 1 12 183 79 1 13 183 76 1 14 183 74 1 15 183 71 2 0 181 66 2 1 180 60 -32768]
	local73
	local74
	local75
	local76
	zombieMover
	local78
	[local79 46] = [136 132 126 133 110 144 131 150 139 168 152 189 319 189 217 140 171 159 165 156 183 134 172 132 181 126 227 124 219 112 199 109 193 103 155 101 154 7 114 7 114 105 187 113 133 122]
	[local125 40] = [105 149 145 189 303 189 164 134 173 125 196 121 216 123 223 117 204 113 191 107 147 101 187 89 187 2 182 2 182 88 125 103 163 108 196 113 157 119 134 131]
	[local165 44] = [0 189 0 -10 319 -10 319 189 196 141 167 149 174 134 163 133 167 128 215 116 188 107 145 101 145 10 131 10 131 103 181 110 199 115 144 126 137 133 122 148 157 162 162 189]
	[local209 113] = [0 0 7 38 0 1 0 38 0 2 2 40 0 3 0 39 1 0 0 41 1 1 6 38 1 2 17 38 2 0 27 38 2 1 38 40 2 2 47 39 3 0 54 41 3 1 66 38 3 2 77 38 4 0 87 38 4 1 98 40 4 2 107 39 5 0 114 41 5 1 126 38 5 2 137 38 6 0 147 38 6 1 158 40 6 2 167 39 7 0 174 41 7 1 186 38 7 2 197 38 8 0 207 38 8 1 218 40 8 2 227 39 -32768]
	[local322 53] = [1 0 114 79 1 1 116 79 1 2 120 69 1 3 130 62 1 4 140 53 1 5 152 48 1 6 182 52 1 7 175 73 1 8 165 82 1 9 156 97 1 10 134 111 1 11 121 110 1 12 111 98 -32768]
	[local375 82] = [0 0 109 82 0 1 108 84 0 2 108 82 0 3 108 82 1 0 114 79 1 1 116 79 1 2 120 69 1 3 130 62 1 4 140 53 1 5 153 48 1 6 182 52 1 7 175 73 1 8 165 82 1 9 156 97 1 10 134 111 1 11 121 110 1 12 111 98 -32768 2 0 138 69 2 1 159 87 2 2 163 106 -32768]
	[local457 53] = [2 3 175 104 3 0 187 76 3 1 191 71 3 2 193 64 3 3 207 54 3 4 220 50 3 5 231 22 3 6 253 14 3 7 284 44 3 8 262 60 3 9 248 55 3 10 241 44 4 0 243 26 -32768]
)
(procedure (localproc_0c46 param1)
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
	)
	(curRoom
		addObstacle:
			(switch (if (not argc) local73 else param1)
				(0
					(roomPoly type: 2 points: @local165 size: 22 yourself:)
				)
				(1
					(roomPoly type: 3 points: @local79 size: 23 yourself:)
				)
				(2
					(roomPoly type: 3 points: @local125 size: 20 yourself:)
				)
			)
	)
)

(instance rm630 of KQ6Room
	(properties
		noun 5
		picture 630
		north 640
		south 600
	)
	
	(method (init)
		(theGame handsOff:)
		(super init: &rest)
		(if (== prevRoomNum 640)
			(= local73 2)
			(ego
				init:
				reset: 5
				setScale: Scaler 40 20 140 80
				posn: 178 92
				setPri: 8
			)
			(curRoom setScript: fromEntranceScr)
		else
			(= local73 0)
			(ego
				init:
				reset: 3
				posn: 225 188
				setScale: FixedScaler 100
			)
			(theGame handsOn:)
		)
		(localproc_0c46)
		(bats
			init:
			illegalBits: 0
			ignoreActors: 1
			ignoreHorizon: 1
			setCycle: MoveCycle @local209 bats
		)
		(zombie
			init:
			illegalBits: 0
			ignoreActors: 1
			ignoreHorizon: 1
			setCycle: Walk
			hide:
			setScript: zombieScript 0 600
		)
		(features
			add: moon sky uWorldEntrance myPath
			eachElementDo: #init
		)
		(if (not (ego has: 17))
			(motherGhost
				init:
				ignoreActors: 1
				illegalBits: 0
				ignoreHorizon: 1
				setScript: motherGhostScript
			)
			(theMusic number: 630 loop: -1 play:)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (not script)
			(switch (= temp0 (ego onControl: 1))
				(16384
					(self setScript: toEntranceScr)
				)
				(8192
					(if (== local73 0) (self setScript: downToMidScr))
				)
				(4096
					(if (== local73 1) (self setScript: downToBottomScr))
				)
				(2048
					(if (== local73 1) (self setScript: upToTopScr))
				)
				(1024
					(if (== local73 2) (self setScript: upToMidScr))
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(LoadMany 0 942 964)
	)
	
	(method (newRoom n)
		(if (== (theMusic number?) 630) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance roomPoly of Polygon
	(properties)
)

(class FixedScaler of Code
	(properties
		frontSize 0
		backSize 0
		frontY 0
		backY 0
	)
	
	(method (init param1 theFrontSize)
		(= backSize (- (= frontSize theFrontSize) 1))
		(= theFrontSize (/ (* theFrontSize 128) 100))
		(param1 scaleX: theFrontSize scaleY: theFrontSize)
	)
)

(instance downToMidScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local74 ((ego mover?) finalX?))
					(= local75 ((ego mover?) finalY?))
					(= local76 ((ego mover?) caller?))
				)
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 1)
			)
			(1
				(ego
					setScale:
						Scaler
						(/ (* (ego scaleX?) 100) 128)
						(- (/ (* (ego scaleX?) 100) 128) 1)
						(ego x?)
						(- (ego x?) 1)
					setPri: 9
					setLoop: 7
					setMotion: MoveTo (- (ego x?) 20) (+ (ego y?) 50) self
				)
			)
			(2
				(ego setLoop: -1 setScale: FixedScaler 65)
				(localproc_0c46 1)
				(= seconds 2)
			)
			(3
				(ego posn: 136 175 setMotion: MoveTo 139 151 self)
			)
			(4
				(= local73 1)
				(if (not local76) (theGame handsOn:))
				(if local74
					(if
					(> (GetDistance (ego x?) (ego y?) local74 local75) 15)
						(ego setMotion: PolyPath local74 local75 local76)
					)
					(= local74 0)
					(= local76 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance downToBottomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local74 ((ego mover?) finalX?))
					(= local75 ((ego mover?) finalY?))
					(= local76 ((ego mover?) caller?))
				)
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 1)
			)
			(1
				(ego
					setScale:
						Scaler
						(/ (* (ego scaleX?) 100) 128)
						(- (/ (* (ego scaleX?) 100) 128) 1)
						(ego x?)
						(- (ego x?) 1)
					setPri: 8
					setLoop: 3
					setMotion: MoveTo 160 167 self
				)
			)
			(2
				(ego setLoop: -1 setScale: Scaler 40 20 140 80)
				(localproc_0c46 2)
				(= seconds 2)
			)
			(3
				(ego posn: 161 151 setMotion: MoveTo 160 134 self)
			)
			(4
				(= local73 2)
				(if (not local76) (theGame handsOn:))
				(if local74
					(if
					(> (GetDistance (ego x?) (ego y?) local74 local75) 10)
						(ego setMotion: PolyPath local74 local75 local76)
					)
					(= local74 0)
					(= local76 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance upToTopScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local74 ((ego mover?) finalX?))
					(= local75 ((ego mover?) finalY?))
					(= local76 ((ego mover?) caller?))
				)
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 1)
			)
			(1
				(ego
					setMotion: MoveTo (- (ego x?) 10) (+ (ego y?) 35) self
				)
			)
			(2
				(localproc_0c46 0)
				(= seconds 2)
			)
			(3
				(ego
					setScale: FixedScaler 100
					setLoop: 2
					posn: 133 191
					setMotion: MoveTo 167 151 self
				)
			)
			(4
				(ego
					setPri: -1
					setLoop: -1
					setMotion: MoveTo 175 156 self
				)
			)
			(5
				(= local73 0)
				(if (not local76) (theGame handsOn:))
				(if local74
					(if
					(> (GetDistance (ego x?) (ego y?) local74 local75) 20)
						(ego setMotion: PolyPath local74 local75 local76)
					)
					(= local74 0)
					(= local76 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance upToMidScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (ego mover?) ((ego mover?) isKindOf: PolyPath))
					(= local74 ((ego mover?) finalX?))
					(= local75 ((ego mover?) finalY?))
					(= local76 ((ego mover?) caller?))
				)
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 1)
			)
			(1
				(ego
					setMotion: MoveTo (+ (ego x?) 3) (+ (ego y?) 24) self
				)
			)
			(2 (= seconds 2))
			(3
				(ego
					setScale: FixedScaler 65
					setLoop: 2
					posn: 172 165
					setMotion: MoveTo 162 136 self
				)
			)
			(4
				(ego
					setLoop: -1
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 154 141 self
				)
			)
			(5
				(= local73 1)
				(localproc_0c46)
				(if (not local76) (theGame handsOn:))
				(if local74
					(if
					(> (GetDistance (ego x?) (ego y?) local74 local75) 15)
						(ego setMotion: PolyPath local74 local75 local76)
					)
					(= local74 0)
					(= local76 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance fromEntranceScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: DPath 176 91 140 103 176 108 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toEntranceScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: DPath 140 103 176 91 self)
			)
			(1
				(ego setMotion: 0)
				(= cycles 2)
			)
			(2
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance zombie of Actor
	(properties
		x 343
		y 258
		noun 11
		view 600
	)
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(!= (motherGhost script?) getHankyScript)
				(<= (ego distanceTo: self) 10)
				(not local73)
			)
			(theGame handsOff:)
			(= local78 1)
			(curRoom setScript: egoDeadScript 0 self)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if local73
				(messager say: noun theVerb 7 1)
			else
				(theGame handsOff:)
				(curRoom setScript: egoDeadScript 0 self)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance ghoul1 of Actor
	(properties
		x 183
		y 156
		noun 8
		view 631
		loop 1
	)
)

(instance zombieScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds (Random 20 30)))
			(1
				(client
					view: register
					show:
					setMotion: DPath 343 258 299 187 234 183 165 186 165 242 self
				)
				(++ register)
				(if (== register 602) (= register 603))
				(if (> register 603) (= register 600))
			)
			(2 (client hide:) (self init:))
		)
	)
)

(instance egoDeadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(zombie setMotion: 0)
				(if local78
					(messager say: 9 0 5 1 self)
				else
					(messager say: 11 5 0 1 self)
				)
			)
			(1
				(cond 
					(local78 (= cycles 1))
					((> (ego x?) (register x?))
						(ego
							setMotion: PolyPath (+ (register x?) 20) (register y?) self
						)
					)
					(else
						(ego
							setMotion: PolyPath (- (register x?) 20) (register y?) self
						)
					)
				)
				(theMusic stop:)
				(theGlobalSound number: 601 loop: 1 play:)
			)
			(2
				(if local78
					(messager say: 9 0 5 2 self)
				else
					(messager say: 11 5 0 2 self)
				)
			)
			(3
				(ego
					view: 606
					normal: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 15
					setCycle: EndLoop self
				)
			)
			(4 (EgoDead 38))
		)
	)
)

(instance ghoul1Scr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(client show: setCycle: MoveCycle @local0 self)
			)
			(1
				(client hide:)
				(= seconds (Random 20 30))
			)
			(2 (self init:))
		)
	)
)

(instance myPath of Feature
	(properties
		noun 4
		onMeCheck $7c02
	)
)

(instance uWorldEntrance of Feature
	(properties
		noun 10
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(super doVerb: theVerb &rest)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(else  (super doVerb: 5 &rest))
		)
	)
)

(instance sky of Feature
	(properties
		noun 7
		onMeCheck $0010
	)
)

(instance moon of Feature
	(properties
		noun 3
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(super doVerb: theVerb &rest)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(5
				(super doVerb: theVerb &rest)
			)
			(else 
				(messager say: noun 0 0 0)
			)
		)
	)
)

(instance bats of Actor
	(properties
		x 7
		y 38
		noun 8
		view 630
	)
	
	(method (cue)
		(ghoul1
			init:
			setPri: 10
			cycleSpeed: 15
			ignoreActors: 1
			ignoreHorizon: 1
			setScript: ghoul1Scr
		)
		(self dispose:)
	)
)

(instance motherGhost of Actor
	(properties
		x 109
		y 82
		noun 2
		view 633
		cycleSpeed 10
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(== (script state?) 0)
				(script register?)
			)
			(theGame handsOff:)
			(self setScript: getHankyScript)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(cond 
					((ego has: 17) (messager say: 2 2 1))
					((!= (script state?) 0) (theGame handsOff:) (script register: 1))
					(else (theGame handsOff:) (self setScript: getHankyScript))
				)
			)
			(1
				(if (ego has: 17)
					(messager say: noun theVerb 1)
				else
					(messager say: noun theVerb 2)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance motherGhostScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 0 posn: 109 82 cycleSpeed: 20 setCycle: Forward)
				(= seconds (Random 5 15))
			)
			(1 (messager say: 9 0 4 1 self))
			(2
				(client cycleSpeed: 5 setCycle: MoveCycle @local322 self)
			)
			(3 (self init:))
		)
	)
)

(instance getHankyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= zombieMover (zombie mover?))
				(zombie mover: 0)
				(ego setMotion: PolyPath 205 162 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(theConv
					add: -1 2 2 2 1
					add: -1 2 2 2 2
					add: -1 2 2 2 3
					add: -1 2 2 2 4
					add: -1 2 2 2 5
					add: -1 2 2 2 6
					add: -1 2 2 2 7
					init: self
				)
			)
			(3 (= cycles 2))
			(4 (messager say: 2 2 2 8 self))
			(5 (messager say: 2 2 2 9 self))
			(6
				(motherGhost cycleSpeed: 15 setCycle: MoveCycle @local375 self)
			)
			(7
				(motherGhost setLoop: 2 cel: 0 posn: 138 69)
				(ego
					normal: 0
					view: 633
					setLoop: 5
					cel: 0
					posn: (- (ego x?) 11) (- (ego y?) 1)
				)
				(= cycles 10)
			)
			(8
				(motherGhost cel: 1 posn: 159 87)
				(ego cel: 1)
				(= cycles 10)
			)
			(9
				(motherGhost cel: 2 posn: 163 106)
				(ego cel: 2)
				(= cycles 10)
			)
			(10
				(motherGhost setCycle: MoveCycle @local457 self)
				(ego cel: 3)
				(= cycles 3)
			)
			(11
				(theGame givePoints: 1)
				(ego
					get: 17
					oldScaleSignal: 0
					reset: 7
					setScale: FixedScaler 100
					posn: 210 167
				)
			)
			(12
				(motherGhost
					view: 632
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(theMusic fade:)
			)
			(13
				(theMusic number: 600 loop: -1 play:)
				(theGame handsOn:)
				(motherGhost dispose:)
				(if zombieMover (zombie mover: zombieMover))
				(= zombieMover 0)
			)
		)
	)
)

(instance theConv of Conversation
	(properties)
)
