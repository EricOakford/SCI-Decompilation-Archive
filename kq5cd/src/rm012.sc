;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Avoider)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm012 0
)

(local
	local0
	local1
	local2
	[local3 6] = [0 178 59 189 0 189]
	[local9 12] = [96 160 144 167 145 172 98 175 51 170 51 165]
	[local21 8] = [0 109 61 113 62 124 0 124]
	[local29 8] = [0 60 68 65 43 79 0 79]
	[local37 6] = [123 48 143 60 94 60]
	[local43 18] = [319 127 197 135 172 138 123 138 108 123 166 99 251 97 251 40 319 40]
	[local61 10] = [319 189 254 189 284 176 195 156 319 137]
	[local71 10] = [1 961 0 962 1 963 0 964]
	[local81 6] = [0 3049 1 960]
	[local87 9] = [1022 200 64 2 9 28 29 25 34]
	[local96 9] = [1000 140 25 4 11 24 19 23 30]
	[local105 9] = [1003 170 70 4 11 25 23 31 31]
	[local114 9] = [1003 10 62 4 11 25 23 31 31]
)
(instance rm012 of KQ5Room
	(properties
		picture 12
		horizon 100
		north 13
		east 10
		south 11
		west 14
	)
	
	(method (init)
		(super init:)
		(= global320 155)
		(= global321 51)
		(= global325 3051)
		(self setFeatures: anthill setRegions: 202)
		(switch prevRoomNum
			(east
				(ego posn: 315 135)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 165 187)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego posn: 85 105)
				(self setScript: (ScriptID 202 1))
			)
			(else 
				(ego posn: -20 141)
				(HandsOff)
				(self setScript: relievedScript)
			)
		)
		(if (!= ((inventory at: 6) owner?) 200)
			(wagon init: stopUpd:)
		)
		(LoadMany 128 338 325)
		(ego ignoreHorizon: 1 illegalBits: 0 init:)
		(if
			(and
				(or (ego has: 8) (ego has: 16))
				(not (Btst 106))
			)
			(LoadMany 128 332)
			(Load rsSCRIPT 991)
			(if (== (theGame detailLevel:) 3)
				(dog init: setScript: dogScript)
			else
				(dog init: stopUpd:)
			)
			(dogHead setPri: 9 init: hide:)
			(ants2 cycleSpeed: 1)
			(ants3 cycleSpeed: 1)
			(ants4 cycleSpeed: 1)
			(ants5 cycleSpeed: 1)
			(theMusic number: 14 loop: -1 vol: 127 playBed:)
		else
			(theMusic number: 13 loop: -1 vol: 127 playBed:)
		)
		(ants1 setCycle: Fwd init:)
		(ants2 setCycle: Fwd init:)
		(ants3 setCycle: Fwd init:)
		(ants4 setCycle: Fwd init:)
		(ants5 setCycle: Fwd init:)
		(poly1 points: @local3 size: 3)
		(poly2 points: @local9 size: 6)
		(poly3 points: @local21 size: 4)
		(poly4 points: @local29 size: 4)
		(poly5 points: @local37 size: 3)
		(poly6 points: @local43 size: 9)
		(poly7 points: @local61 size: 5)
		(self
			addObstacle: poly1 poly2 poly3 poly4 poly5 poly6 poly7
		)
	)
	
	(method (doit)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= local0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (== local0 (curRoom west?))
					(self setScript: goingToDesert)
				else
					(theMusic fade:)
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				)
			)
			(
				(and
					(< 169 (ego x?))
					(< (ego x?) 231)
					(< 81 (ego y?))
					(< (ego y?) 140)
					(or
						(< (ego distanceTo: ants1) 10)
						(< (ego distanceTo: ants2) 10)
						(< (ego distanceTo: ants3) 10)
						(< (ego distanceTo: ants4) 10)
						(< (ego distanceTo: ants5) 10)
					)
				)
				(HandsOff)
				(self setScript: antDance)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 991)
		(DisposeScript 985)
		(DisposeScript 970)
		(DisposeScript 951)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance warnScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(SpeakAudio 273)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance relievedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(ego setMotion: MoveTo 7 141 self)
			)
			(2
				(proc762_0 @local96 @local114 @local81 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance antDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					view: 338
					setLoop: 0
					normal: 0
					cel: 0
					setStep: 1 1
					setMotion: MoveTo 188 145
					setCycle: End self
				)
			)
			(1
				(ego cycleSpeed: 5 cel: 0 setLoop: 1 setCycle: End self)
			)
			(2
				(ego cel: 0 setLoop: 2 setCycle: End self)
			)
			(3
				(cls)
				(ego cel: 0 setLoop: 3 setCycle: End self)
			)
			(4 (ego setCycle: End self))
			(5
				(ego
					view: 0
					setCel: -1
					normal: 1
					setLoop: -1
					setStep: 3 2
					setCycle: KQ5SyncWalk
				)
				(= cycles 1)
			)
			(6
				(ego loop: 2)
				((ego head?) show:)
				(= cycles 5)
			)
			(7
				(proc762_1 @local96 3052 self)
			)
			(8
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance throwStick of Script
	(properties)
	
	(method (doit &tmp [temp0 2])
		(super doit: &rest)
		(if (and (== state 1) (== (ego cel?) 8))
			(stick
				setLoop:
					(switch local2
						(2 (+ 14 (ego loop?)))
						(3 (+ 10 (ego loop?)))
						(0 (+ 4 (ego loop?)))
					)
				x: (if (< (ego x?) 181)
					(+ (ego x?) 24)
				else
					(- (ego x?) 23)
				)
				y: (- (ego y?) 15)
				setCycle: Walk
				setPri: (ego priority?)
				setMotion: JumpTo 120 138 self
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					(
						(or
							(& (ego onControl: 0) $0004)
							(& (ego onControl: 0) $0010)
						)
						(ego setMotion: PolyPath 200 161 self)
					)
					((& (ego onControl: 0) $0002) (= cycles 1))
					(else (ego setMotion: PolyPath 23 136 self))
				)
			)
			(1
				(SpeakAudio 969 0 1)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 296
					loop: (if (< (ego x?) 151) 0 else 1)
					cel: 0
					setCycle: End self
				)
				(stick
					setLoop:
						(switch local2
							(2 (+ 12 (ego loop?)))
							(3 (+ 8 (ego loop?)))
							(0 (+ 2 (ego loop?)))
						)
					view: (ego view?)
					x: (ego x?)
					y: (ego y?)
					cel: 0
					setCycle: End
					cycleSpeed: (ego cycleSpeed?)
					init:
				)
			)
			(2)
			(3
				(ego normal: 1 view: 0 setCycle: KQ5SyncWalk)
				((ego head?) show:)
				(Face ego stick)
				(stick setPri: -1 cel: 6 stopUpd:)
				(dog setScript: 0)
				(dogScript client: 0)
				(= cycles 1)
			)
			(4
				(dogHead dispose:)
				(dog
					setStep: 4 4
					setLoop: 5
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors: 1
					setMotion: MoveTo 135 143 self
				)
				(theAudio number: 8861 loop: 1 play:)
			)
			(5
				(dog
					view: 332
					posn: 129 146
					setLoop: 0
					setCycle: CT 5 1 self
				)
				(theAudio number: 8861 loop: 1 play:)
			)
			(6
				(cls)
				(stick dispose:)
				(dog setCycle: End self)
				(theAudio number: 8861 loop: 1 play:)
			)
			(7
				(dog
					view: 330
					ignoreActors: 0
					setLoop: 4
					illegalBits: -32768
					setCycle: Walk
					setAvoider: ((Avoid new:) offScreenOK: 1)
					setMotion: MoveTo -45 143 self
				)
				(theMusic fade:)
			)
			(8
				(dog dispose:)
				(ego setMotion: PolyPath 185 142 self)
			)
			(9
				(theMusic3 stop:)
				(ants2 cycleSpeed: 2)
				(ants3 cycleSpeed: 2)
				(ants4 cycleSpeed: 2)
				(ants5 cycleSpeed: 2)
				(Face ego cloud 5)
				(= cycles 3)
			)
			(10
				(proc762_0 @local105 @local87 @local71 self)
			)
			(11
				(dogHead dispose:)
				(cls)
				(= cycles 3)
			)
			(12
				(= globalCedric 50)
				(theMusic fade:)
				(= cycles 20)
			)
			(13
				(Bset 106)
				(theMusic number: 13 loop: -1 vol: 127 playBed:)
				(HandsOn)
				(ego setStep: 3 2)
				(client setScript: 0)
				(dog setAvoider: 0 dispose:)
			)
		)
	)
)

(instance dogScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dogHead hide:)
				(dog loop: 6 setCycle: Fwd)
				(= seconds (Random 3 8))
			)
			(1
				(dogHead show: loop: 3 cycleSpeed: 4 setCycle: Fwd)
				(dog loop: 0)
				(theAudio number: 8861 loop: 1 play: self)
			)
			(2 (self init:))
		)
	)
)

(instance growlAtEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register)
					(++ register)
					(cls)
					(proc762_1 @local96 3013)
				)
				(dog loop: 0)
				(dogHead
					show:
					loop: 1
					cel: 0
					cycleSpeed: 4
					setCycle: End self
				)
			)
			(1
				(theAudio number: 8863 loop: 1 play: self)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 330
					x: (if (< (ego x?) (dog x?))
						(- (ego x?) 7)
					else
						(+ (ego x?) 7)
					)
					y: (+ (ego y?) 1)
					loop: (if (< (ego x?) (dog x?)) 7 else 8)
					cel: 0
					setMotion: 0
					cycleSpeed: 3
					setCycle: End
				)
			)
			(2
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					loop: (if (== (ego loop?) 7) 0 else 1)
				)
				(= cycles 1)
				((ego head?) show:)
				(dogHead setCycle: Beg self)
			)
			(3 (= seconds 2))
			(4
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					loop: 0
				)
				((ego head?) show:)
				(if (> (theGame detailLevel:) 0)
					(dog setScript: dogScript)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1 (= cycles 10))
			(2 (client setCycle: Beg self))
			(3
				(= state -1)
				(= seconds (Random 3 10))
			)
		)
	)
)

(instance anthill of RFeature
	(properties
		nsTop 82
		nsLeft 173
		nsBottom 127
		nsRight 245
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 274)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 281)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 277)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(16
							(SpeakAudio 279)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 279)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance dogHead of Prop
	(properties
		x 230
		y 123
		view 330
		loop 3
	)
)

(instance dog of Actor
	(properties
		x 243
		y 130
		view 330
		loop 6
		cycleSpeed 1
		detailLevel 3
		illegalBits $0000
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					script
					(== cel 1)
					(!= ((inventory at: 16) owner?) 12)
				)
				(theMusic3 number: 862 loop: 1 vol: 127 play:)
			)
			((curRoom script?) 0)
			((not local1) (= local1 1) (HandsOff) (curRoom setScript: warnScript))
			(
				(and
					(< (ego distanceTo: self) 50)
					(not (& (ego onControl: 0) $0010))
				)
				(HandsOff)
				(self setScript: 0)
				(curRoom setScript: growlAtEgoScript)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 275)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 278)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 282)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(16
							(theIconBar enable:)
							(User canControl: 0)
							(ego put: 16 12)
							(= local2 0)
							(SolvePuzzle 4)
							(curRoom setScript: throwStick)
							(event claimed: 1)
						)
						(8
							(theIconBar enable:)
							(User canControl: 0)
							(SolvePuzzle 4)
							(ego put: 8 12)
							(= local2 2)
							(curRoom setScript: throwStick)
							(event claimed: 1)
						)
						(19
							(theIconBar enable:)
							(User canControl: 0)
							(ego put: 19 12)
							(= local2 3)
							(curRoom setScript: throwStick)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 280)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance wagon of Prop
	(properties
		x 75
		y 37
		view 328
		loop 5
		signal $0001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 276)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wAnt of Prop
	(properties)
)

(instance eAnt of Prop
	(properties)
)

(instance theMouth of Prop
	(properties)
)

(instance kingAnt of Prop
	(properties
		x 231
		y 80
		view 325
		priority 13
		signal $0011
	)
)

(instance stick of Actor
	(properties
		view 296
		signal $6000
	)
)

(instance blink of Prop
	(properties
		x 229
		y 43
		view 325
		loop 4
		priority 14
		signal $0010
	)
)

(instance wArm of Prop
	(properties
		x 211
		y 69
		view 325
		loop 5
		cel 6
		priority 14
		signal $0010
	)
)

(instance eArm of Prop
	(properties
		x 256
		y 74
		view 325
		loop 6
		cel 6
		priority 14
		signal $0010
	)
)

(instance ants1 of Prop
	(properties
		x 221
		y 128
		view 328
		priority 9
		signal $0010
		cycleSpeed 2
	)
)

(instance ants2 of Prop
	(properties
		x 179
		y 129
		view 328
		loop 1
		priority 9
		signal $0010
		cycleSpeed 2
	)
)

(instance ants3 of Prop
	(properties
		x 191
		y 130
		view 328
		loop 2
		priority 9
		signal $0010
		cycleSpeed 2
	)
)

(instance ants4 of Prop
	(properties
		x 192
		y 91
		view 328
		loop 3
		priority 9
		signal $0010
		cycleSpeed 2
	)
)

(instance ants5 of Prop
	(properties
		x 194
		y 126
		view 328
		loop 4
		priority 9
		signal $0010
		cycleSpeed 2
	)
)

(instance cloud of Prop
	(properties
		x 231
		y 52
		view 214
		priority 12
		signal $0010
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)

(instance poly6 of Polygon
	(properties
		type $0002
	)
)

(instance poly7 of Polygon
	(properties
		type $0002
	)
)

(instance goingToDesert of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(proc762_1 @local96 3048 self)
			)
			(1
				(theMusic fade:)
				(HandsOn)
				(curRoom newRoom: local0)
			)
		)
	)
)
