;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm044 0
)

(local
	local0
	local1
	local2
	local3
	local4
	[local5 2]
	[local7 34] = [235 0 198 73 236 93 230 124 170 152 89 136 87 126 179 109 179 96 96 76 147 65 121 65 92 58 81 52 188 42 173 40 174]
	[local41 30] = [110 42 108 47 9 58 64 66 65 61 102 64 78 75 20 76 54 132 118 179 90 189 0 189 0 0 155 0 155 38]
	[local71 10] = [132 91 156 94 139 104 85 104 76 94]
	[local81 8] = [319 186 242 186 233 176 319 176]
	[local89 9] = [1000 10 10 4 11 24 19 23 30]
	[local98 9] = [1003 10 10 4 11 25 23 31 31]
	[local107 6] = [0 3067 1 7026]
)
(instance rm044 of KQ5Room
	(properties
		picture 44
		north 45
		east 47
		south 46
	)
	
	(method (init)
		(super init:)
		(= global325 3066)
		(= global320 15)
		(= global321 72)
		(if (not (ego has: 30))
			(ironBar init: ignoreActors: setPri: 2 stopUpd:)
		)
		(self setRegions: 220 setFeatures: path44 beach ocean)
		(if (== gGNumber_2 45) (smallBoat init: setPri: 1))
		(if
			(and
				(not (Btst 54))
				(not (curRoom script?))
				(not (Btst 105))
			)
			(self setRegions: 202)
		)
		(if (== (theGame detailLevel:) 3)
			(theAudio doNotStop: 1 number: 7055 loop: -1 play:)
			(waterFall
				x: 242
				y: 50
				init:
				view: 614
				setLoop: 1
				setPri: 0
				cycleSpeed: 4
				setCycle: Fwd
			)
		)
		(surf1 setScript: waves)
		(LoadMany 128 0 26 28)
		(switch prevRoomNum
			(south
				(ego view: 0 posn: 130 187)
				(if
				(and (cast contains: gPolyList15) (not (Btst 105)))
					(self setScript: (ScriptID 202 1))
				)
			)
			(north
				(ego view: 0 posn: 246 114)
				(if (cast contains: gPolyList15)
					(self setScript: (ScriptID 202 1))
				)
			)
			(east 0)
			(43 (ego posn: -10 160))
			(else  (ego posn: 146 150))
		)
		(features eachElementDo: #init)
		(if (== prevRoomNum 43)
			(ego
				init:
				signal: 24576
				illegalBits: 0
				cycleSpeed: 2
				posn: -10 160
				setCycle: Fwd
				setPri: (+ (eagle priority?) 1)
				normal: 0
				view: 616
				setLoop: 1
			)
			((ego head?) hide:)
			(self setScript: flyIn)
		else
			(ego init: illegalBits: -32768)
		)
		(poly1 points: @local7 size: 17)
		(poly2 points: @local41 size: 15)
		(poly3 points: @local71 size: 5)
		(poly4 points: @local81 size: 4)
		(self addObstacle: poly1 poly2 poly3 poly4)
		(if
			(and
				(Btst 105)
				(!= curRoom gGNumber_2)
				(not (curRoom script?))
			)
			(self setScript: walkThru)
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			((& (= temp1 (ego onControl: 0)) $0400)
				(if (> (ego x?) 99)
					(self setScript: changeDown)
				else
					(self setScript: changeUp)
				)
			)
			((& temp1 $4000)
				(if (cast contains: gPolyList15)
					((ScriptID 202 2) register: 1)
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: 45)
				)
			)
			(
			(and (& (ego onControl: 1) $2000) (!= (ego view?) 26)) (ego view: 26))
			(
				(and
					(!= (ego view?) 0)
					(!= (ego view?) 2)
					(not (ego script?))
					(not (& temp1 $2040))
				)
				(ego view: 0)
			)
			((& temp1 $0002) (curRoom setScript: fall))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if
				(and (cast contains: gPolyList15) (!= temp0 47))
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				else
					(Bset 107)
					(curRoom newRoom: temp0)
				)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(if (not (OneOf n 44 45 46)) (theMusic fade:))
	)
)

(instance walkThru of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: setMotion: PolyPath 237 105 self)
			)
			(1 (curRoom newRoom: 45))
		)
	)
)

(instance changeUp of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 9
					setLoop: 1
					setCycle: End self
					illegalBits: 0
					moveSpeed: 0
					cel: 0
					posn: 73 54
					setMotion: MoveTo 123 43
				)
				((ego head?) hide:)
			)
			(1
				(ego
					view: 2
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: 1
					illegalBits: -32768
					moveSpeed: (theGame egoMoveSpeed?)
					setMotion: MoveTo 123 43 self
				)
				((ego head?) show:)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance changeDown of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 9
					setLoop: 0
					setCycle: End self
					cel: 0
					moveSpeed: 0
					illegalBits: 0
					posn: 113 44
					setMotion: MoveTo 68 55
				)
				((ego head?) hide:)
			)
			(1
				(ego
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: -1
					illegalBits: -32768
					moveSpeed: (theGame egoMoveSpeed?)
					setMotion: MoveTo 68 55 self
				)
				((ego head?) show:)
			)
			(2
				(ego setMotion: PolyPath 69 60 self)
			)
			(3
				(HandsOn)
				(if (ego script?) (ego setScript: getBar))
				(client setScript: 0)
			)
		)
	)
)

(instance waves of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(surf1
					init:
					show:
					setCycle: End self
					setPri: 2
					ignoreActors:
					cycleSpeed: 10
				)
				(surf2
					init:
					setCycle: RandCycle
					setPri: 2
					ignoreActors:
					cycleSpeed: 10
				)
				(surf3
					init:
					setCycle: RandCycle
					setPri: 2
					ignoreActors:
					cycleSpeed: 10
				)
				(surf4
					init:
					setCycle: RandCycle
					setPri: 2
					ignoreActors:
					cycleSpeed: 10
				)
			)
			(1
				(surf1 setCycle: Beg self)
				(surf2 setCycle: Beg)
				(surf3 setCycle: Beg)
				(surf4 setCycle: Beg)
			)
			(2 (= state -1) (= seconds 1))
		)
	)
)

(instance fall of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(if (== (ego loop?) 1) (= local4 1) else (= local4 0))
				(if (< (ego y?) 90) (= local3 1) else (= local3 0))
				(ego
					view: (if (== (ego view?) 0) 100 else 97)
					normal: 0
					setStep: 2 7
					cycleSpeed: (if local3 1 else 1)
					setPri: (if local3 2 else 10)
					illegalBits: 0
					posn: (+ (ego x?) 10) (ego y?)
					ignoreActors:
					setLoop: 0
					cel: 0
					setCycle: CT 3 1
				)
				(= cycles 1)
			)
			(1
				(DoAudio 1 8892)
				(ego
					setMotion:
						MoveTo
						(if local4 (- (ego x?) 25) else (+ (ego x?) 15))
						(if local3 (+ (ego y?) 35) else (+ (ego y?) 28))
						self
				)
			)
			(2
				(theAudio number: 8892 loop: 1 play:)
				(= seconds 3)
			)
			(3
				(ego setLoop: 2 cel: 0 setCycle: End self)
				(if (== (theGame detailLevel:) 3)
					(theAudio doNotStop: 1 number: 7055 loop: -1 play:)
				)
			)
			(4
				(ego
					setLoop: (+ local4 6)
					posn: (+ (ego x?) 2) (+ (ego y?) 1)
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(5
				(if (== (ego view?) 97)
					(ego
						view: 2
						setLoop: -1
						loop: 3
						setCycle: KQ5SyncWalk
						setStep: 3 2
						cycleSpeed: 0
						setMotion: MoveTo 205 119 self
					)
					((ego head?) show:)
				else
					(= cycles 1)
				)
			)
			(6
				(if local3
					(ego
						normal: 1
						view: 0
						setLoop: -1
						loop: 3
						setCycle: KQ5SyncWalk
						setStep: 3 2
						cycleSpeed: 0
						setMotion: MoveTo 235 125 self
					)
					((ego head?) show:)
				else
					(= cycles 1)
				)
			)
			(7
				(ego
					normal: 1
					view: 0
					setLoop: -1
					setPri: -1
					illegalBits: -32768
					loop: 3
					setCycle: KQ5SyncWalk
					setStep: 3 2
					cycleSpeed: 0
				)
				(ego loop: 2)
				((ego head?) show:)
				(HandsOn)
				(= cycles 1)
			)
			(8 (client setScript: 0))
		)
	)
)

(instance flyIn of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if local1
			(ego posn: (+ (eagle x?) 1) (- (eagle y?) 4))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 15)
				(HandsOff)
				(eagle setPri: 0 init:)
				(= local1 1)
			)
			(1
				(eagle setCycle: Fwd setMotion: MoveTo 160 5 self)
			)
			(2
				(= local1 0)
				(ego
					init:
					setLoop: 2
					cel: 0
					setPri: 2
					cycleSpeed: 0
					setStep: 3 4
					setCycle: End
					setMotion: MoveTo (ego x?) (+ (ego y?) 10) self
				)
			)
			(3
				(theAudio number: 8892 loop: 1 play: self)
				(eagle setLoop: 3 setPri: 15 setMotion: MoveTo 340 0 self)
			)
			(4
				(theAudio doNotStop: 1 number: 7055 loop: -1 play:)
			)
			(5
				((ego head?) show:)
				(ego
					view: 2
					setCycle: KQ5SyncWalk
					posn: (ego x?) (+ (ego y?) 33)
					setPri: -1
					setLoop: -1
					setMotion: PolyPath 102 47 self
				)
			)
			(6
				((ego head?) hide:)
				(ego view: 9 setLoop: 0)
				(theMusic fade:)
				(= cycles 1)
			)
			(7
				(ego
					setCycle: End self
					setMotion: MoveTo (- (ego x?) 40) (+ (ego y?) 10) self
				)
			)
			(8
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					setMotion: PolyPath 70 79 self
				)
			)
			(9
				(ego view: 0 loop: 7 cel: 2 setPri: -1)
				(= cycles 5)
			)
			(10
				(eagle dispose:)
				(proc762_0 @local89 @local98 @local107 self)
			)
			(11
				(HandsOn)
				(ego illegalBits: -32768)
				(cls)
				(self dispose:)
			)
		)
	)
)

(instance getBar of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (& (ego onControl: 1) $2040))
				(or (== (ego view?) 26) (== (ego view?) 28))
			)
			(ego view: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: PolyPath 150 170 self
				)
			)
			(1
				((ego head?) hide:)
				(ego view: 56 loop: 0 cel: 0 normal: 0 cycleSpeed: 1)
				(= cycles 1)
			)
			(2 (ego setCycle: End self))
			(3
				(ironBar dispose:)
				(ego setCycle: Beg self)
			)
			(4
				(ego get: 30)
				(ego
					view: 0
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					illegalBits: -32768
				)
				((ego head?) show:)
				(SolvePuzzle 2)
				(= cycles 1)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance ocean of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $2000))
					(not (& (OnControl 4 (event x?) (event y?)) $0040))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 506)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path44 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 507)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance beach of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0100))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 506)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance waterFall of Prop
	(properties
		x 237
		y 50
		view 614
		loop 1
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
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 508)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance ironBar of Prop
	(properties
		x 160
		y 172
		view 893
		loop 1
		cel 5
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
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 509)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setScript: getBar)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance eagle of Actor
	(properties
		x -18
		y 13
		view 616
		priority 14
		signal $6000
		cycleSpeed 3
		illegalBits $0000
	)
)

(instance surf1 of Prop
	(properties
		x 275
		y 189
		view 614
		detailLevel 3
	)
)

(instance surf2 of Prop
	(properties
		x 261
		y 74
		view 614
		loop 2
		detailLevel 3
	)
)

(instance surf3 of Prop
	(properties
		x 299
		y 121
		view 614
		loop 3
		detailLevel 3
	)
)

(instance surf4 of Prop
	(properties
		x 298
		y 152
		view 614
		detailLevel 3
	)
)

(instance poly1 of Polygon
	(properties)
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

(instance smallBoat of View
	(properties
		x 221
		y 37
		view 614
		loop 4
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
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 510)
					(event claimed: 1)
				)
			)
		)
	)
)
