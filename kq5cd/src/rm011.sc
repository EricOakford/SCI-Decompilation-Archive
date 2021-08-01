;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include sci.sh)
(use Main)
(use Intrface)
(use Waters)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Avoid)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm011 0
)

(local
	local0
	local1
	local2
	[theX 6] = [261 188 80 141 188 80]
	local9
	[local10 12] = [0 167 71 175 108 159 142 157 168 189 0 189]
	[local22 16] = [0 122 86 118 108 124 175 126 145 140 109 140 46 149 0 149]
	[local38 10] = [319 107 251 105 205 100 216 91 319 89]
	local48
	[local49 6] = [0 3049 1 960]
	[local55 9] = [1000 126 33 4 11 24 19 23 30]
	[local64 9] = [1006 40 50 5 10 32 26 31 30]
	[local73 6] = [0 1053 0 1054]
	[local79 9] = [1003 10 62 4 11 25 23 31 31]
)
(instance rm011 of KQ5Room
	(properties
		picture 11
		horizon 115
		north 12
		east 27
		west 14
	)
	
	(method (init)
		(super init:)
		(= global320 141)
		(= global321 66)
		(= global325 3045)
		(self setFeatures: tree anthill setRegions: 202)
		(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
		(if (== (theGame detailLevel:) 3) (water init:))
		(if (ego has: 5)
			(LoadMany 128 320)
			(Load rsSCRIPT 991)
			(bear init: setScript: bearScript)
		else
			(LoadMany 128 314 316)
		)
		(if (== ((inventory at: 16) owner?) 11)
			(stick init: stopUpd:)
		)
		(hive
			init:
			stopUpd:
			onMeCheck:
				((Polygon new:)
					init: 98 64 105 80 99 88 89 87 81 81 85 73
					yourself:
				)
		)
		(bees
			ignoreHorizon: 1
			ignoreActors: 1
			setCycle: Fwd
			init:
		)
		(theMusic number: 16 loop: -1 vol: 127 play:)
		(switch prevRoomNum
			(east
				(ego posn: 314 130)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego posn: 165 118)
				(self setScript: (ScriptID 202 1))
			)
			(else 
				(ego posn: -20 164)
				(HandsOff)
				(self setScript: relievedScript)
			)
		)
		(ego view: 0 ignoreHorizon: 1 setStep: 3 2 init:)
		(poly1 points: @local10 size: 6)
		(poly2 points: @local22 size: 8)
		(poly3 points: @local38 size: 5)
		(self addObstacle: poly1 poly2 poly3)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((not local9)
				(++ local9)
				(cond 
					((cast contains: bear)
						(if (not (Btst 85))
							(Bset 85)
							(self setScript: warnAboutBear)
						)
					)
					((and (not (Btst 86)) (not (Btst 36))) (Bset 86) (proc762_1 @local55 3047))
				)
			)
			(
				(and
					(ego edgeHit?)
					(= local0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (== local0 (curRoom west?))
					(self setScript: goingToDesert)
				else
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				)
			)
			((& (= temp0 (ego onControl: 0)) $0002) (HandsOff) (self setScript: drownHim))
			(
				(and
					(& temp0 $0010)
					(not (Btst 36))
					(not (cast contains: bear))
				)
				(HandsOff)
				(self setScript: deathByBees)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		((hive onMeCheck?) dispose:)
		(DisposeScript 985)
		(DisposeScript 991)
		(DisposeScript 401)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(or
				script
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 257)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego
						setMotion: PolyPath (if (< (ego x?) 172) 172 else (ego x?)) 189
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance relievedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ego setMotion: MoveTo 7 164 self)
			)
			(2
				(proc762_0 @local55 @local79 @local49 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance drownHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local55 3032 self)
			)
			(1
				(DoAudio 1 8068)
				(= cycles 1)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 69
					loop: (if (and (< (ego x?) 251) (> (ego x?) 203)) 1 else 0)
					cel: 0
					setPri: 15
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(theAudio number: 8068 loop: 1 play:)
				(ego
					setLoop: 2
					x:
						(if (and (< (ego x?) 251) (> (ego x?) 203))
							(- (ego x?) 39)
						else
							(+ (ego x?) 39)
						)
					y: (+ (ego y?) 22)
					setCycle: Fwd
					setStep: 3 1
					illegalBits: -32768
					setMotion: PolyPath (ego x?) 225 self
					cycleSpeed: 2
				)
			)
			(4
				(if (!= (DoAudio 6) -1)
					(-- state)
				else
					(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				)
				(= cycles 1)
			)
			(5 (= seconds 3))
			(6
				(cls)
				(= deathMessage 254)
				(EgoDead)
			)
		)
	)
)

(instance throwFish of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 1) (== (ego cel?) 7))
			(fish
				init:
				setLoop: 2
				x: (if (< (ego x?) 181)
					(+ (ego x?) 24)
				else
					(- (ego x?) 7)
				)
				y: (- (ego y?) 4)
				cel: (if (< (ego x?) 181) 4 else 4)
				setCycle: Walk
				setPri: (ego priority?)
				setMotion: JumpTo 181 138 self
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& (ego onControl: 0) $0400)
					(ego setMotion: PolyPath 20 159 self)
				else
					(ego setMotion: PolyPath 296 127 self)
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 320
					loop: (if (< (ego x?) 181) 0 else 1)
					cel: 0
					setCycle: End self
				)
			)
			(2)
			(3
				(ego
					normal: 1
					view: 0
					loop: (if (< (ego x?) 181) 0 else 1)
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(theAudio number: 9301 loop: 1 play: self)
				(fish setPri: -1 cel: 0 stopUpd:)
			)
			(4
				(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(SolvePuzzle 4)
				(= local1 2)
				(client setScript: 0)
			)
		)
	)
)

(instance bearScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (== state 6) (== -1 (theMusic prevSignal?))) (self cue:))
			((and (== state 5) (== (bear cel?) 1))
				((ego head?) hide:)
				(ego
					normal: 0
					view: 304
					setLoop: 3
					cel: 0
					cycleSpeed: 0
					setCycle: End
				)
			)
			((and (== local1 2) (< state 7)) (= state 7) (self cue:))
			((and (== (bear cel?) 2) (== state 9)) (fish dispose:))
			(
				(and
					(> (ego y?) (bear y?))
					(& (ego onControl: 0) $0040)
					(!= local1 1)
					(< state 4)
				)
				(HandsOff)
				(= local1 1)
				(= state 3)
				(self cue:)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(bear
					view: 1110
					loop: 0
					cycleSpeed: 2
					cel: 0
					setCycle: End self
				)
			)
			(1
				(bear
					view: 1111
					x: 121
					y: 138
					cycleSpeed: 2
					loop: 1
					cel: 0
					setCycle: End self
				)
				(if (== (theGame detailLevel:) 3)
					(= state (Random 0 2))
				else
					(bear stopUpd:)
				)
			)
			(2
				(bear view: 1112 loop: 2 cel: 0 setCycle: End self)
				(= state (Random 0 2))
			)
			(3
				(bear view: 1113 loop: 3 cel: 0 setCycle: End self)
				(cond 
					((== (theAudio number?) 7777)
						(if (not (Random 0 3))
							(theAudio number: 8851 loop: 1 play:)
						)
					)
					((== (DoAudio 6) -1) (theAudio number: 7777 loop: -1 doNotStop: 1 play:))
				)
				(= state (Random 0 2))
			)
			(4
				(theMusic number: 17 loop: 1 vol: 127 play:)
				(bear
					view: 304
					posn: 101 142
					loop: 0
					cel: 0
					cycleSpeed: 0
					setCycle: End self
				)
				(theAudio number: 8851 loop: 1 play:)
			)
			(5
				(bear posn: 92 142 loop: 1 cel: 0 setCycle: End self)
			)
			(6)
			(7
				(if
					(or
						(!= (ego view?) 304)
						(!= (ego cel?) (ego lastCel:))
					)
					(-- state)
					(= cycles 1)
				else
					(= deathMessage 255)
					(EgoDead 262)
				)
			)
			(8
				(bear
					view: 1114
					loop: 4
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(9
				(bear
					view: 308
					loop: 3
					cel: 0
					x: (+ (bear x?) 36)
					setCycle: End self
				)
			)
			(10
				(bear
					setAvoider: ((Avoid new:) offScreenOK: 1)
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 380 (bear y?) self
				)
			)
			(11
				(bear view: 0 setAvoider: 0)
				(= cycles 1)
			)
			(12
				(theMusic fade:)
				(hive hide:)
				(bees hide:)
				(= cycles 1)
			)
			(13
				(Bset 36)
				(theMusic4 number: 63 loop: 1 vol: 127 play:)
				(proc762_0 @local64 @local64 @local73 self)
			)
			(14
				(hive show:)
				(bees show:)
				(= cycles 1)
			)
			(15
				(cls)
				(= cycles 1)
				(theMusic number: 16 loop: -1 vol: 127 play:)
				(theMusic4 fade:)
			)
			(16
				(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(HandsOn)
				(bear dispose:)
				(curRoom setScript: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance getWax of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: 0) $0010)
				(not (Btst 36))
				(not (cast contains: bear))
				(not local48)
			)
			(++ local48)
			(HandsOff)
			(self setScript: deathByBees)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 93 143 self)
			)
			(1
				(ego
					illegalBits: 0
					setPri: 12
					setMotion: MoveTo 94 136 self
				)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 312
					loop: 4
					cel: 0
					cycleSpeed: 2
					setCycle: End self
					get: 17
				)
				(SolvePuzzle 2)
			)
			(3
				(proc0_29 264)
				(ego setCycle: Beg self)
			)
			(4
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 105 148 self
				)
			)
			(5
				(ego setPri: -1 illegalBits: -32768)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance deathByBees of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 1)
				(theMusic number: 15 loop: 1 vol: 127 play:)
				(bees
					view: 314
					loop: 0
					setPri: 14
					posn: (+ ((ego head?) x?) 30) (- ((ego head?) y?) 25)
					cel: 0
					setCycle: End self
				)
			)
			(1
				(bees
					posn: (ego x?) (- (ego y?) 15)
					setLoop: 1
					setCycle: Fwd
				)
				(ego
					view: 316
					normal: 0
					cycleSpeed: 3
					setLoop: 1
					setCycle: End self
				)
				((ego head?) hide:)
			)
			(2
				(ego setLoop: 0 setCycle: End self)
			)
			(3
				(ego setLoop: 2 cel: 0 setMotion: 0 setCycle: End self)
			)
			(4 (= seconds 3))
			(5
				(= deathMessage 256)
				(EgoDead 263)
			)
		)
	)
)

(instance getStick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 146 143 self)
				(if (cast contains: bear) (curRoom setScript: 0))
			)
			(1
				(proc0_29 265)
				((ego head?) hide:)
				(ego normal: 0 view: 56 loop: 3 cel: 0 setCycle: End self)
			)
			(2
				(SolvePuzzle 2)
				(stick dispose:)
				(ego get: 16 setCycle: Beg self)
			)
			(3
				(ego normal: 1 view: 0 setCycle: Walk loop: 7 cel: 1)
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance water of Waters
	(properties
		x 1000
		y 1000
		view 299
		priority 1
		cycleSpeed 5
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 257)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego
						setMotion: PolyPath (if (< (ego x?) 172) 172 else (ego x?)) 189
					)
					(event claimed: 1)
				)
			)
		)
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
		(= cel [theX (++ pos)])
	)
	
	(method (saveLoop)
		(= [theX pos] cel)
		(++ pos)
	)
)

(instance tree of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 258)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hive of Prop
	(properties
		x 93
		y 90
		view 298
		loop 7
		signal $4000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (self onMe: event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 259)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (!= ((inventory at: 17) owner?) 11)
						(proc0_29 266)
					else
						(HandsOff)
						(curRoom setScript: getWax)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance anthill of RFeature
	(properties
		nsTop 64
		nsLeft 202
		nsBottom 75
		nsRight 238
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
					(proc0_29 260)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bear of Actor
	(properties
		x 108
		y 138
		view 1110
		illegalBits $0000
		xStep 5
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
					(proc0_29 261)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 267)
					(event claimed: 1)
				)
				(JOY_DOWN
					(proc0_29 269)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(5
							(ego put: 5 11)
							(HandsOff)
							(ego setScript: throwFish)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(proc0_29 271)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance bees of Actor
	(properties
		x 91
		y 62
		view 312
		priority 10
		signal $4010
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if (and (< (Random 0 100) 50) (not local2))
			(bees loop: (Random 0 2))
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
					(proc0_29 258)
					(event claimed: 1)
				)
				(JOY_DOWN
					(proc0_29 270)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 268)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(proc0_29 272)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance stick of Prop
	(properties
		x 132
		y 149
		view 312
		loop 3
		priority 2
		signal $4011
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
					(proc0_29 263)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getStick)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance fish of Actor
	(properties
		view 320
		loop 2
		signal $4000
	)
)

(instance cloud of Prop
	(properties
		x 94
		y 80
		view 214
		priority 14
		signal $0010
	)
)

(instance body of Prop
	(properties
		x 94
		y 80
		view 298
		cel 1
		priority 10
		signal $0011
	)
	
	(method (init)
		(super init:)
		(theMouth init:)
		(antenna init:)
		(theEyes init: stopUpd:)
		(bodyWest init: stopUpd:)
		(bodyEast init: stopUpd:)
	)
	
	(method (dispose)
		(super dispose:)
		(theMouth dispose:)
		(antenna dispose:)
		(theEyes dispose:)
		(bodyWest dispose:)
		(bodyEast dispose:)
	)
)

(instance theMouth of Prop
	(properties
		x 91
		y 70
		view 298
		loop 1
		cel 4
		priority 11
		signal $0010
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance antenna of Prop
	(properties
		x 90
		y 51
		view 298
		loop 5
		cel 2
		priority 11
		signal $0010
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 1 60)
			(1 (self setCycle: End))
		)
	)
)

(instance theEyes of Prop
	(properties
		x 91
		y 65
		view 298
		loop 6
		priority 12
		signal $0010
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (== 1 (Random 1 30)))
			(self setScript: (moveScript new:))
		)
	)
)

(instance bodyWest of Prop
	(properties
		x 64
		y 72
		view 298
		loop 3
		cel 2
		priority 11
		signal $0010
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (== 1 (Random 1 30)))
			(self setScript: (moveScript new:))
		)
	)
)

(instance bodyEast of Prop
	(properties
		x 104
		y 95
		view 298
		loop 4
		cel 2
		priority 11
		signal $0010
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (== 1 (Random 1 50)))
			(self setScript: (moveScript new:))
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
				(client setScript: 0 stopUpd:)
			)
		)
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

(instance warnAboutBear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local55 3046 self)
			)
			(1
				(if (== (theMusic3 prevSignal?) -1)
					(theAudio number: 8851 loop: 1 play: self)
				else
					(= cycles 1)
				)
			)
			(2
				(theAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(self dispose:)
			)
		)
	)
)

(instance goingToDesert of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(cls)
				(proc762_1 @local55 3048 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: local0)
			)
		)
	)
)
