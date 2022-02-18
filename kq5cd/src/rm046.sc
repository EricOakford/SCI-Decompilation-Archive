;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Reverse)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm046 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 30] = [254 185 254 177 319 177 319 189 0 189 0 0 239 0 242 114 168 121 180 127 153 133 106 165 132 168 133 172 82 185]
	[local36 10] = [244 0 319 0 319 100 253 106 246 98]
	[local46 9] = [1014 116 74 4 10 30 21 24 28]
	[local55 9] = [1003 162 64 4 11 25 23 31 31]
	[local64 9] = [1014 152 74 4 10 30 21 24 28]
	[local73 9] = [1003 191 74 4 11 25 23 31 31]
	[local82 6] = [0 1103 0 1112]
)
(instance rm046 of KQ5Room
	(properties
		picture 46
		north 44
		east 47
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 624 626 628)
		(Load rsSCRIPT 941)
		(= cedricX 100)
		(= cedricY 140)
		(= global325 3070)
		(self setFeatures: house beach bell setRegions: 220)
		(ego posn: 0 500 illegalBits: -16384 init:)
		(switch prevRoomNum
			(112 (self setScript: cartoon2))
			(east
				(if
				(and (not (Btst 54)) (not (curRoom script?)))
					(self setRegions: 202)
				)
			)
			(north
				(if (> (ego x?) 255)
					(ego show: posn: (ego x?) 125)
				else
					(ego show: posn: 255 125)
				)
				(ego setMotion: MoveTo (ego x?) 135 normal: 0)
				(if (not (Btst 54))
					(self setRegions: 202)
					(self setScript: (ScriptID 202 1))
				)
			)
			(else 
				(ego show: posn: 255 125)
				(if (not (Btst 54)) (self setRegions: 202))
			)
		)
		(if (cast contains: globalCedric)
			(globalCedric setPri: 13)
		)
		(chimney init:)
		(if (== (theGame detailLevel:) 3)
			(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
			(surf1 setScript: waves)
			(chimney setCycle: Forward cycleSpeed: 3)
		)
		(if
			(and
				(not (if (Btst 71) (== gGNumber_2 46)))
				(!= prevRoomNum 112)
			)
			(door init: setPri: 9)
		else
			(door init: setPri: 9 hide:)
			(hermit_a view: 626 posn: 131 148 setPri: 10 cel: 2 init:)
		)
		(if (== prevRoomNum east)
			(ego illegalBits: -16384)
		else
			(ego init: view: 0 normal: 1)
		)
		(poly1 points: @local6 size: 15)
		(poly2 points: @local36 size: 5)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0)
		(return
			(cond 
				(script
					(if
					(and (Btst 71) (not local2) (== gGNumber_2 46))
						(= local2 1)
					)
					(script doit:)
				)
				(local2 (= local2 0) (Bclr 71) (self setScript: bringCedric))
				((= temp0 (self edgeToRoom: (ego edgeHit?)))
					(if
						(and
							(cast contains: globalCedric)
							(!= temp0 47)
							((ScriptID 202 2) register: 2)
						)
						(self setScript: (ScriptID 202 2))
					else
						(Bset 107)
						(curRoom newRoom: temp0)
					)
				)
				((ego inRect: 234 100 257 115)
					(ego setMotion: 0)
					(if (cast contains: globalCedric)
						((ScriptID 202 2) register: 1)
						(self setScript: (ScriptID 202 2))
					else
						(curRoom newRoom: 44)
					)
				)
				(
					(and
						(cast contains: hermit_a)
						(== (hermit_a view?) 624)
						(> (ego distanceTo: hermit_a) 80)
					)
					(self setScript: hermitScared)
				)
				(
					(and
						(cast contains: hermit_a)
						(== (hermit_a view?) 624)
						(< (ego distanceTo: hermit_a) 45)
					)
					(self setScript: hermitScared)
				)
				(
				(and (& (ego onControl: 1) $2000) (!= (ego view?) 26)) (ego view: 26))
				(
				(and (& (ego onControl: 1) $0001) (!= (ego view?) 0)) (ego view: 0))
				(local1
					(if
						(and
							(> local1 100)
							(== (hermit_a view?) 624)
							(cast contains: hermit_a)
						)
						(= local1 0)
						(curRoom setScript: hermitScared)
					else
						(++ local1)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(DisposeScript 969)
		(ego ignoreControl: 16384)
		(super dispose:)
	)
	
	(method (handleEvent event)
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

(instance egoHeadMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) loop: (Random 4 6))
				(-- state)
				(= cycles 5)
			)
		)
	)
)

(instance cartoon2 of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 4) (== (theMusic3 prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(hermit_a
					view: 626
					setCycle: Walk
					cycleSpeed: 0
					setLoop: -1
					ignoreActors: 1
					posn: 90 148
					illegalBits: 0
					show:
					init:
				)
				(= cycles 1)
			)
			(1
				(hermit_a setMotion: MoveTo 175 148)
				(= cycles 30)
			)
			(2
				(ego
					view: 624
					posn: 120 148
					normal: 1
					ignoreActors: 1
					illegalBits: 0
					setPri: -1
					setCycle: EndLoop self
					setLoop: 13
					setMotion: MoveTo 130 148 self species
				)
			)
			(3
				((ego head?) hide:)
				(ego
					show:
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 130 148 self
				)
			)
			(4
				((ego head?) show:)
				(ego view: 0 setLoop: 0 setMotion: MoveTo 160 160)
				(hermit_a view: 628 setLoop: 2)
				(arm
					view: 628
					posn: (- (hermit_a x?) 5) (- (hermit_a y?) 32)
					setPri: (+ (hermit_a priority?) 1)
					setLoop: 1
					setCycle: CycleTo 8 1
					init:
				)
				(theMusic3 number: 821 loop: 1 play:)
			)
			(5
				(arm setCycle: EndLoop)
				(hermit_a setLoop: 2)
				(= seconds 5)
			)
			(6
				(mermaid
					init:
					setLoop: 8
					cycleSpeed: 2
					posn: 275 155
					setCycle: EndLoop self
				)
			)
			(7
				(mermaid setLoop: 7 cycleSpeed: 3 setCycle: Forward)
				(= cycles 1)
			)
			(8
				(theMouth
					init:
					setCycle: RandCycle
					setPri: (+ (hermit_a priority?) 1)
					setLoop: (theMouth loop?)
					ignoreActors:
					cycleSpeed: 2
					posn: (+ (hermit_a x?) 3) (- (hermit_a y?) 37)
				)
				(SpeakAudio 1147 self)
			)
			(9
				(theMouth setCycle: 0)
				(cls)
				(= seconds 3)
			)
			(10
				(theMouth setCycle: RandCycle)
				(SpeakAudio 1148 self)
			)
			(11
				(theMouth setCycle: 0)
				(cls)
				((ego head?) setCel: 1 setScript: egoHeadMove)
				(SpeakAudio 9102 self)
			)
			(12
				((ego head?) setCel: 1 loop: 4 setScript: 0)
				(theMouth setCycle: RandCycle)
				(cls)
				(SpeakAudio 1151 self)
			)
			(13
				(theMouth setCycle: 0)
				(cls)
				((ego head?) setCel: 1 setScript: egoHeadMove)
				(SpeakAudio 9104 self)
			)
			(14
				((ego head?) setCel: -1 setScript: 0)
				(cls)
				(mermaid setLoop: 10 cycleSpeed: 2 setCycle: EndLoop self)
				(theAudio number: 9302 play:)
			)
			(15
				(theMouth dispose:)
				(arm hide:)
				(hermit_a
					view: 626
					setCycle: Walk
					setLoop: 1
					cycleSpeed: 0
					ignoreActors: 1
					setMotion: MoveTo 90 144 self
				)
			)
			(16
				(door setCycle: BegLoop self show:)
				(ego setCycle: KQ5SyncWalk setLoop: -1 view: 0)
				(NormalEgo)
			)
			(17
				(if (!= (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(18
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(Bset 105)
				(ego setMotion: PolyPath 237 105 self)
				(door stopUpd:)
			)
			(19 (curRoom newRoom: 44))
			(20 (client setScript: 0))
		)
	)
)

(instance bringCedric of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(proc762_1 @local46 1133)
				(= cycles 1)
			)
			(1
				(cls)
				(= local4 1)
				(client setScript: cartoon)
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
					setCycle: EndLoop self
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(surf2
					init:
					setCycle: RandCycle
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
			)
			(1
				(surf1 setCycle: BegLoop self)
				(surf2 setCycle: BegLoop)
			)
			(2 (= seconds 5))
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance giveShell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 23)
				(SolvePuzzle 4)
				(HandsOff)
				(ego
					setCycle: KQ5SyncWalk
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 186 149 self
				)
			)
			(1
				((ego head?) moveHead: 0)
				(Face ego hermit_a)
				(hermitHead hide:)
				(hermit_a
					view: 626
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 167 149 self
				)
			)
			(2
				(ego hide:)
				((ego head?) hide: moveHead: 1)
				(hermit_a
					view: 624
					setLoop: 6
					cel: 0
					posn: 176 149
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(proc762_1 @local46 1127 self)
			)
			(4
				(hermit_a stopUpd:)
				(cls)
				(curRoom setScript: cartoon)
			)
		)
	)
)

(instance dPush of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: 1) $0001)
				(or (== (ego view?) 26) (== (ego view?) 28))
			)
			(ego view: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 135 147 self illegalBits: 0)
			)
			(1
				((ego head?) hide:)
				(ego view: 628 loop: 3 cycleSpeed: 1 setCycle: Forward)
				(theAudio number: 8828 loop: -1 play:)
				(= seconds 4)
			)
			(2
				(theMusic3 stop:)
				(theAudio stop:)
				(++ local0)
				(SpeakAudio 515)
				((ego head?) show:)
				(ego
					view: 0
					cel: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					ignoreActors: -32768
				)
				(= cycles 1)
			)
			(3
				(HandsOn)
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(client setScript: 0)
			)
		)
	)
)

(instance myRotate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalCedric view: 138 loop: 1 setCycle: EndLoop self)
			)
			(1
				(globalCedric view: 138 loop: 4 cel: 7 setCycle: BegLoop self)
			)
			(2
				(globalCedric stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance hermitScared of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(-- global362)
				(= cycles 1)
			)
			(1
				(proc762_1 @local46 1134)
				(= cycles 1)
			)
			(2
				(cls)
				(hermit_a
					view: 626
					setCycle: Walk
					cycleSpeed: 0
					setLoop: -1
					setCel: -1
					setMotion: MoveTo 105 148 self
				)
			)
			(3
				(hermit_a dispose:)
				(HandsOn)
				(= cycles 3)
			)
			(4
				(if (< global362 12)
					(++ global362)
				else
					(= global362 0)
				)
				(= cycles 1)
			)
			(5
				(door startUpd: ignoreActors: 0 setCycle: BegLoop self)
			)
			(6
				(if (!= (DoAudio 6) -1) (-- state))
				(door stopUpd:)
				(= cycles 1)
			)
			(7
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(client setScript: 0)
			)
		)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local4 (= state 5))
				(= cycles 1)
			)
			(1
				(Hhead hide:)
				((ego head?) show:)
				(ego
					show:
					setCycle: Reverse
					setLoop: 1
					setMotion: MoveTo (+ (ego x?) 20) (ego y?) self
				)
				(hermit_a
					view: 624
					setLoop: 4
					posn: (- (ego x?) 19) (ego y?)
					setCycle: EndLoop
				)
				(shell
					init:
					signal: 22
					setPri: (- (hermit_a priority?) 1)
				)
			)
			(2
				(ego cel: 1 setCycle: 0)
				(= cycles 5)
			)
			(3
				(proc762_1 @local64 1128 self)
			)
			(4
				(cls)
				(if (Btst 55)
					(proc762_1 @local73 1129 self)
				else
					(proc762_1 @local73 1130 self)
				)
			)
			(5
				(cls)
				(cond 
					((Btst 55) (proc762_1 @local64 1131) (= state 8))
					((!= gGNumber_2 46) (proc762_1 @local64 1132) (= state 10))
					(else (proc762_1 @local64 9113))
				)
				(= cycles 1)
			)
			(6
				(cls)
				(ego
					ignoreActors: 1
					setCycle: KQ5SyncWalk
					setLoop: -1
					normal: 0
					illegalBits: 0
					setMotion: MoveTo 260 168 self
				)
			)
			(7
				(ego setLoop: 1)
				(= cycles 1)
			)
			(8
				((ego head?) hide:)
				(ego
					view: 624
					loop: 1
					posn: 260 155
					setPri: 15
					normal: 0
					setCycle: EndLoop self
				)
			)
			(9
				(cls)
				(shell dispose:)
				(hermit_a
					view: 626
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 0
					ignoreActors: 1
					setMotion: MoveTo 90 148
				)
				(ego
					setLoop: (if (Btst 55) -1 else 2)
					setPri: 10
					setCycle: KQ5SyncWalk
					normal: 0
					ignoreActors: 1
					setMotion: MoveTo 135 146 self
				)
			)
			(10
				(ego
					view: 624
					setLoop: (if (Btst 55) 14 else 11)
					setCycle: EndLoop self
				)
			)
			(11
				(cls)
				(if (== (hermit_a view?) 624)
					(shell dispose:)
					(hermit_a
						view: 626
						setLoop: -1
						setCycle: Walk
						cycleSpeed: 0
						ignoreActors: 1
						setMotion: MoveTo 90 148 self
					)
				else
					(if (Btst 55)
						(ego view: 0 setLoop: 1 setCel: 0)
					else
						(ego setLoop: 2)
					)
					(ego setCycle: KQ5SyncWalk setMotion: MoveTo 90 148 self)
				)
			)
			(12
				(door cycleSpeed: 2 setCycle: BegLoop self)
			)
			(13
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(= seconds 2)
			)
			(14
				(client setScript: 0)
				(cond 
					((Btst 55) (curRoom newRoom: 662))
					((!= gGNumber_2 46)
						(NormalEgo)
						(HandsOn)
						(Bset 71)
						(hermit_a dispose:)
						(= local1 0)
					)
					(else (curRoom newRoom: 661))
				)
			)
		)
	)
)

(instance greeting of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: 1) $0001)
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
					normal: 0
					setMotion: PolyPath 185 128 self
				)
			)
			(1
				(if (or (> global362 13) (< global362 0))
					(= global362 0)
				)
				(ego setMotion: PolyPath 177 129 self)
			)
			(2
				(ego setLoop: 7 setCel: 1)
				((ego head?) setLoop: 4)
				(arm
					init:
					posn: (- (ego x?) 4) (- (ego y?) 30)
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(arm setCycle: MouthSync 8819)
				(theAudio number: 8819 loop: 1 play: self)
			)
			(4
				(arm dispose:)
				(= seconds (Random 2 6))
			)
			(5
				((ego head?) setLoop: -1 cel: 4)
				(ego view: 0 loop: 7 cel: 2)
				(door startUpd: ignoreActors: 1 setCycle: EndLoop self)
			)
			(6 (= seconds 1))
			(7
				(ego stopUpd:)
				(door stopUpd:)
				(hermit_a
					view: 626
					setCycle: Walk
					setMotion: MoveTo 131 148 self
					setPri: 10
					init:
				)
				(theMusic2 number: 827 loop: 1 play:)
			)
			(8
				(hermit_a cel: 2)
				(RedrawCast)
				(= cycles 1)
			)
			(9
				(theAudio stop:)
				(switch global362
					(0
						(proc762_1 @local46 1089 self)
					)
					(1
						(proc762_1 @local46 1090 self)
					)
					(2
						(proc762_1 @local46 1091 self)
					)
					(3
						(proc762_1 @local46 1092 self)
					)
					(4
						(proc762_1 @local46 1093 self)
					)
					(5
						(proc762_1 @local46 1094 self)
					)
					(6
						(proc762_1 @local46 1095 self)
					)
					(7
						(proc762_1 @local46 1096 self)
					)
					(8
						(proc762_1 @local46 1097 self)
					)
					(9
						(proc762_1 @local46 1098 self)
					)
					(10
						(proc762_1 @local46 1099 self)
					)
					(11
						(proc762_1 @local46 1100 self)
					)
					(12
						(proc762_1 @local46 1101 self)
					)
				)
			)
			(10
				(cls)
				(HandsOn)
				(ego normal: 1)
				(NormalEgo)
				(++ local1)
				(= cycles 1)
			)
			(11
				(client setScript: talkHermit)
			)
		)
	)
)

(instance talkHermit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego stopUpd:)
				(= local1 0)
				(switch global362
					(0
						(proc762_1 @local55 1113 self)
					)
					(1
						(proc762_1 @local55 1114 self)
					)
					(2
						(proc762_1 @local55 1115 self)
					)
					(3
						(proc762_1 @local55 1116 self)
					)
					(4
						(proc762_1 @local55 1117 self)
					)
					(5
						(proc762_1 @local55 1118 self)
					)
					(6
						(proc762_1 @local55 1119 self)
					)
					(7
						(proc762_1 @local55 1120 self)
					)
					(8
						(proc762_1 @local55 1121 self)
					)
					(9
						(proc762_1 @local55 1122 self)
					)
					(10
						(proc762_1 @local55 1123 self)
					)
					(11
						(proc762_1 @local55 1124 self)
					)
					(12
						(proc762_1 @local55 1129 self)
					)
				)
			)
			(1 (cls) (= cycles 1))
			(2
				(switch global362
					(0
						(proc762_1 @local46 1102 self)
					)
					(1
						(proc762_0 @local46 @local46 @local82 self)
					)
					(2
						(proc762_1 @local46 1104 self)
					)
					(3
						(proc762_1 @local46 1105 self)
					)
					(4
						(proc762_1 @local46 1106 self)
					)
					(5
						(proc762_1 @local46 1107 self)
					)
					(6
						(proc762_1 @local46 1108 self)
					)
					(7
						(proc762_1 @local46 1109 self)
					)
					(8
						(proc762_1 @local46 1110 self)
					)
					(else 
						(proc762_1 @local46 1111 self)
					)
				)
			)
			(3
				(if (< global362 12)
					(++ global362)
				else
					(= global362 0)
				)
				(= cycles 1)
			)
			(4 (HandsOff) (= cycles 1))
			(5
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(HandsOn)
				(User canControl: 0)
				(= seconds (if (ego has: 23) 7 else 1))
			)
			(6
				(HandsOff)
				(cls)
				(hermit_a
					view: 626
					illegalBits: 0
					setPri: 10
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 95 148 self
				)
			)
			(7
				(door startUpd: ignoreActors: 0 setCycle: BegLoop self)
				(theMusic2 fade:)
			)
			(8
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(9
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(door stopUpd:)
				(HandsOn)
				(hermit_a dispose:)
				(= seconds 2)
			)
			(10 (client setScript: 0))
		)
	)
)

(instance house of RFeature
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
					(SpeakAudio 516)
					(event claimed: 1)
				)
				(JOY_RIGHT (event claimed: 0))
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 517)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance beach of RFeature
	(properties
		nsTop 89
		nsLeft 242
		nsBottom 188
		nsRight 318
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $2000))
					(not (& (OnControl 4 (event x?) (event y?)) $0040))
					(not (& (OnControl 4 (event x?) (event y?)) $0001))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 518)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hermit_a of Actor
	(properties
		x 95
		y 148
		view 626
		signal $0001
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (cast contains: shell) (not (hermit_a mover?)))
			(switch (hermit_a cel?)
				(0
					(shell x: (+ (hermit_a x?) 8) y: (- (hermit_a y?) 20))
				)
				(1
					(shell
						x: (+ (hermit_a x?) 13)
						y: (- (hermit_a y?) 23)
					)
				)
				(2
					(shell
						x: (+ (hermit_a x?) 15)
						y: (- (hermit_a y?) 35)
					)
				)
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
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 519)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(23
							(curRoom setScript: giveShell)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 522)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance door of Door
	(properties
		x 113
		y 147
		view 625
		signal $4000
		openDoorNumber 8123
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0004))
					(not (MousedOn self event))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 520)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 517)
							(event claimed: 1)
						)
					)
				)
				(JOY_RIGHT
					(if (event claimed: 1)
						(curRoom setScript: dPush)
					else
						(event claimed: 0)
					)
				)
			)
		)
	)
)

(instance bell of RFeature
	(properties
		x 163
		y 94
		z 32
		nsTop 88
		nsLeft 152
		nsBottom 102
		nsRight 173
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (MousedOn self event))
					(not (& (OnControl 4 (event x?) (event y?)) $0200))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 521)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cond 
						((cast contains: hermit_a) (event claimed: 0))
						((Btst 71) (event claimed: 0))
						(else (curRoom setScript: greeting) (event claimed: 1))
					)
				)
			)
		)
	)
)

(instance hermitHead of Prop
	(properties
		view 628
		loop 2
		priority 15
		signal $0001
	)
)

(instance chimney of Prop
	(properties
		x 18
		y 70
		z 20
		view 625
		loop 2
		detailLevel 3
	)
)

(instance Hhead of Prop
	(properties
		view 628
		signal $4001
	)
)

(instance arm of Prop
	(properties
		view 624
		loop 3
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

(instance surf1 of Prop
	(properties
		x 284
		y 155
		view 623
		loop 1
		detailLevel 3
	)
)

(instance surf2 of Prop
	(properties
		x 261
		y 181
		view 623
		loop 2
		detailLevel 3
	)
)

(instance hermit_b of Actor
	(properties
		signal $0001
	)
)

(instance mermaid of Actor
	(properties
		view 624
		signal $0001
	)
)

(instance theMouth of Prop
	(properties
		view 624
		loop 15
	)
)

(instance shell of View
	(properties
		view 624
		loop 5
	)
)
