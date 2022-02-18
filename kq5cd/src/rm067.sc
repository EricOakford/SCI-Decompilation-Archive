;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use Intrface)
(use castle)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm067 0
)

(local
	local0
	local1
	local2
	[local3 9] = [141 35 159 165 34 165 183 40 163]
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	local19
	[local20 16] = [92 149 75 159 37 156 32 120 32 0 319 0 319 168 192 151]
	[local36 12] = [319 189 0 189 0 181 260 182 302 172 319 175]
	[local48 14] = [0 105 15 153 69 167 25 178 6 171 6 177 0 177]
	[local62 9] = [1012 5 100 4 9 31 26 29 32]
	[local71 9] = [1003 116 62 4 11 25 23 31 31]
	[local80 12] = [0 1073 1 1074 0 1075 1 1076 0 1077]
)
(procedure (localproc_000e)
	(= [local71 1] (Min (Max 0 (- (ego x?) 40)) 239))
)

(instance rm067 of KQ5Room
	(properties
		picture 67
	)
	
	(method (init)
		(super init:)
		(Load VIEW 697)
		(Load VIEW 695)
		(Load VIEW 902)
		(Load VIEW 882)
		(Load VIEW 904)
		(Load SOUND 97)
		(Load SOUND 62)
		(= globalCedric actor_1)
		(self setRegions: 550 setFeatures: mouseHole hole grate)
		(switch prevRoomNum
			(55
				(stone init: setPri: 13 stopUpd:)
				(stone cel: (stone lastCel:) forceUpd:)
				(curRoom setScript: enterHole)
			)
			(else 
				(stone init: setPri: 5 stopUpd:)
				(self addObstacle: poly4)
				(ego
					init:
					hide:
					view: 882
					ignoreActors: 1
					illegalBits: 0
					setLoop: 14
					setCel: 1
					posn: 1000 1000
				)
				((ego head?) moveHead: 0 hide:)
				(Load SOUND 67)
				(CastleHandsOff)
				(self setScript: henchCaught)
			)
		)
		(dripple setCel: 255 setPri: 5 init: hide:)
		(splash setCycle: Forward init: hide:)
		(poly2 points: @local20 size: 8)
		(poly3 points: @local36 size: 6)
		(poly4 points: @local48 size: 7)
		(self addObstacle: poly2 poly3 poly4)
	)
	
	(method (doit &tmp egoX egoY)
		(reflection
			loop: (ego loop?)
			cel: (ego cel?)
			x: (ego x?)
			y: (+ (ego y?) 1)
		)
		(if
		(and (& (ego onControl: 1) $0004) (ego mover?))
			(= egoX (ego x?))
			(= egoY (ego y?))
			(switch (ego loop?)
				(0 (= egoX (+ egoX 1)))
				(1 (= egoX (- egoX 1)))
				(3 (= egoY (- egoY 2)))
				(2 (= egoY (+ egoY 1)))
			)
			(splash
				cycleSpeed: (ego cycleSpeed?)
				x: egoX
				y: egoY
				show:
			)
		else
			(splash hide:)
		)
		(cond 
			(script (script doit:))
			(
				(and
					(& (ego onControl: 0) $0010)
					(== (stone cel?) (stone lastCel:))
				)
				(self setScript: enteringHole)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			((== (event type?) 16384)
				(switch (event message?)
					(verbLook
						(if (& (OnControl 4 (event x?) (event y?)) $0004)
							(SpeakAudio 9050)
						else
							(SpeakAudio 9051)
						)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance drippingWater of Script
	(properties)
	
	(method (doit &tmp dropMover dropMoverDy)
		(super doit:)
		(if
			(and
				(= dropMover (drop mover?))
				(< (= dropMoverDy (dropMover dy?)) 16)
			)
			(dropMover dy: (* dropMoverDy 2))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= seconds (Random 3 10))
				(= local0 [local3 (= temp0 (* (Random 0 2) 3))])
				(= local1 [local3 (++ temp0)])
				(= local2 [local3 (++ temp0)])
			)
			(1
				(dripple
					loop: 0
					cel: 0
					posn: local0 local1
					cycleSpeed: 0
					setCycle: CycleTo 2 1 self
					show:
				)
			)
			(2
				(drop
					posn: local0 (+ local1 4)
					yStep: 1
					setMotion: MoveTo local0 local2 self
					show:
				)
				(dripple setCycle: EndLoop)
			)
			(3
				(drop hide:)
				(theAudio number: 8098 loop: 1 play:)
				(dripple
					posn: local0 local2
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance moveStone of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (== local12 ego) (not (-- local15)))
			(if cycles
				(= temp0 (+ local13 (= local14 (^ local14 $0001))))
				(= local15 2)
			else
				(= temp0 local13)
				(= local12 0)
			)
			((ego head?) loop: temp0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 20 40)))
			(1
				(if
					(or
						(== (curRoom script?) lookInMseHole)
						(& (ego onControl: 0) $0018)
						(< (ego x?) 55)
					)
					(= seconds 8)
					(= cycles 1)
					(= state 0)
				else
					(= cycles 1)
				)
				(theMusic fade:)
			)
			(2
				(CastleHandsOff)
				(ego observeControl: 8 setMotion: 0)
				(theAudio number: 7065 play:)
				(= local13 ((ego head?) loop?))
				(stone setPri: 5 cycleSpeed: 6 setCycle: CycleTo 4 1 self)
			)
			(3
				(Face ego stone 0)
				(theMusic number: 97 loop: 1 vol: 127 play:)
				(theAudio number: 8892 play:)
				(stone setPri: 13 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(4
				((ego head?)
					cel: (& (StrAt ((ego head?) headCel?) (ego cel?)) $000f)
					moveHead: 0
				)
				(stone setPri: -1 stopUpd:)
				(= seconds 3)
			)
			(5
				((curRoom obstacles?) delete: poly4)
				(globalCedric
					init:
					view: 882
					setLoop: 8
					setCel: 0
					setPri: 5
					posn: 20 144
					xStep: 1
					cycleSpeed: 2
					setMotion: MoveTo 24 144 self
					show:
				)
				(prop1
					view: 882
					loop: 10
					posn: 22 136
					setCycle: Forward
					setPri: 11
					init:
					hide:
				)
			)
			(6
				(globalCedric setCycle: CycleTo 4 1 self)
			)
			(7
				(localproc_000e)
				(proc762_0 @local62 @local71 @local80 self)
			)
			(8
				(globalCedric setCycle: EndLoop self)
				(= local14 2)
			)
			(9
				(globalCedric setLoop: 9 cel: 0 setCycle: EndLoop self)
			)
			(10
				(globalCedric setCycle: BegLoop self)
			)
			(11
				(if (-- local14)
					(= state (- state 2))
				else
					(globalCedric setLoop: 8)
				)
				(= cycles 1)
			)
			(12
				(globalCedric
					cel: (globalCedric lastCel:)
					setCycle: BegLoop self
				)
				(theMusic fade:)
			)
			(13
				(globalCedric setCel: 0 setMotion: MoveTo 20 144 self)
			)
			(14
				(theMusic number: 67 loop: -1 vol: 127 play:)
				(Bset 69)
				(Bset 96)
				(globalCedric dispose: delete:)
				((ego head?) moveHead: 1)
				(CastleHandsOn)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
				(client setScript: 0)
			)
		)
	)
)

(instance enteringHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					normal: 0
					illegalBits: 0
					ignoreActors: 1
					setPri: 5
					setMotion: MoveTo 33 155 self
				)
			)
			(1
				(ego
					view: 882
					loop: 12
					cel: 0
					ignoreActors: 1
					setCycle: EndLoop self
				)
				((ego head?) hide:)
			)
			(2
				(ego
					setLoop: 15
					cel: 0
					xStep: 1
					setCycle: KQ5SyncWalk
					moveSpeed: 1
					setMotion: MoveTo 21 155 self
				)
			)
			(3
				(CastleHandsOn)
				(curRoom newRoom: 55)
			)
		)
	)
)

(instance lookInMseHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego setMotion: MoveTo 183 150 self)
			)
			(1
				(ego
					view: 882
					loop: 5
					cel: 0
					posn: (- (ego x?) 10) (+ (ego y?) 1)
					normal: 0
					setCycle: EndLoop self
				)
				((ego head?) hide:)
				(reflection hide:)
			)
			(2
				(if (and (not local19) (not local16))
					(globalCedric
						view: 214
						setLoop: 0
						cel: 0
						posn: 156 144
						setPri: 14
						setStep: 6 4
						cycleSpeed: 2
						setMotion: MoveTo 119 126
						setCycle: CycleTo 4 1 self
						init:
						show:
					)
				else
					(= state (+ state 2))
					(= cycles 1)
				)
			)
			(3
				(User canInput: 1)
				(User canControl: 0)
				(theIconBar enable:)
				(= isHandsOff 0)
				(= inCartoon 0)
				(globalCedric setMotion: 0 hide:)
				(prop1
					view: 882
					loop: 1
					cel: 1
					posn: (globalCedric x?) (+ (globalCedric y?) 5)
					setPri: (+ (globalCedric priority?) 1)
					setCycle: 0
					init:
					show:
				)
				(= cycles 2)
			)
			(4
				(SpeakAudio 691)
				(if (not local16)
					(= local18 1)
					(= state (+ state 3))
					(= seconds 5)
				else
					(= cycles 1)
				)
			)
			(5
				(CastleHandsOff)
				(ego setMotion: 0 loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(6
				(if (not local19)
					(cond 
						(local16 (SpeakAudio 698) (= local16 0))
						(local17 (SpeakAudio 699) (++ local17))
						(else (SpeakAudio 700) (++ local17))
					)
				else
					(SpeakAudio 705)
					(= local17 2)
					(SolvePuzzle 4)
					(ego get: 32)
				)
				(ego setCycle: BegLoop self)
			)
			(7
				(CastleHandsOff)
				(ego loop: 5 cel: (ego lastCel:) setMotion: 0)
				(= cycles 1)
			)
			(8
				(= local18 0)
				(globalCedric hide:)
				(prop1 hide:)
				(ego setCycle: BegLoop self)
			)
			(9
				(CastleHandsOn)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
				(ego
					view: 0
					loop: 7
					posn: (+ (ego x?) 10) (- (ego y?) 1)
					setCycle: KQ5SyncWalk
					normal: 1
				)
				((ego head?) show:)
				(Face ego prop1 5)
				(reflection show:)
				(client setScript: 0)
			)
		)
	)
)

(instance mouseRunning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic3 number: 62 loop: 1 play:)
				(globalCedric
					view: 882
					setLoop: 4
					posn: 64 189
					setPri: 5
					setStep: 4 3
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 64 164 self
					init:
					show:
				)
			)
			(1
				(globalCedric setLoop: 3 posn: 61 159)
				(= cycles 10)
			)
			(2
				(globalCedric setMotion: MoveTo 119 148 self)
			)
			(3 (= cycles 10))
			(4
				(globalCedric setMotion: MoveTo 153 148 self)
			)
			(5 (= cycles 10))
			(6
				(globalCedric
					setLoop: 4
					posn: 157 152
					setPri: 4
					setMotion: MoveTo 157 138 self
				)
			)
			(7 (client setScript: 0))
		)
	)
)

(instance drop of Actor
	(properties
		view 882
		loop 1
		priority 12
		signal $5810
		illegalBits $0000
	)
)

(instance actor_1 of Actor
	(properties
		signal $6000
		illegalBits $0000
	)
)

(instance prop1 of Prop
	(properties
		signal $4000
	)
	
	(method (handleEvent event)
		(if
			(or
				(not local18)
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 692)
					(event claimed: 1)
					(return)
				)
				(verbDo
					(if (< local17 2) (event claimed: 1))
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(31
							(if (ego has: 32)
								(SpeakAudio 706)
								(event claimed: 1)
							else
								(= local19 1)
								(lookInMseHole start: 2)
								(rm067 setScript: lookInMseHole)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 707)
							(event claimed: 1)
						)
					)
				)
			)
			(if (event claimed?)
				((rm067 script?)
					state: (- ((rm067 script?) state?) 3)
					seconds: 0
					cycles: 1
				)
			)
		)
	)
)

(instance reflection of Prop
	(properties
		x 319
		y 189
		view 883
		signal $4000
	)
)

(instance dripple of Prop
	(properties
		view 882
		signal $4000
		detailLevel 3
	)
)

(instance stone of Prop
	(properties
		x 24
		y 176
		view 882
		loop 7
		signal $4000
		cycleSpeed 2
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
				(verbLook
					(cond 
						((== (stone cel?) (stone lastCel:)) (SpeakAudio 693) (event claimed: 1))
						((Btst 96) (SpeakAudio 694) (event claimed: 1))
						(else (event claimed: 0))
					)
				)
				(verbDo
					(cond 
						((== (stone cel?) (stone lastCel:)) (SpeakAudio 701) (event claimed: 1))
						((Btst 96) (SpeakAudio 702) (event claimed: 1))
						(else (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance splash of Prop
	(properties
		view 882
		loop 13
		signal $4000
		cycleSpeed 1
	)
)

(instance mouseHole of RFeature
	(properties
		nsTop 141
		nsLeft 151
		nsBottom 147
		nsRight 163
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
				(verbLook
					(cond 
						((ego has: 32) (SpeakAudio 695) (event claimed: 1))
						((== (curRoom script?) lookInMseHole) (event claimed: 0))
						(else (rm067 setScript: lookInMseHole) (event claimed: 1))
					)
				)
				(verbDo
					(if (not (ego has: 32))
						(switch local17
							(0
								(= local16 1)
								(if (== (curRoom script?) lookInMseHole)
									(lookInMseHole start: 2)
								else
									(lookInMseHole start: 0)
								)
								(rm067 setScript: lookInMseHole)
								(event claimed: 1)
							)
							(1
								(SpeakAudio 699)
								(event claimed: 1)
							)
						)
					else
						(SpeakAudio 703)
						(event claimed: 1)
					)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(31
							(if (not (ego has: 32))
								(= local19 1)
								(if (== (curRoom script?) lookInMseHole)
									(lookInMseHole start: 2)
								else
									(lookInMseHole start: 0)
								)
								(rm067 setScript: lookInMseHole)
								(event claimed: 1)
							else
								(SpeakAudio 703)
								(event claimed: 1)
							)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 707)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance hole of RFeature
	(properties
		nsTop 127
		nsLeft 14
		nsBottom 146
		nsRight 26
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (stone cel?) (stone lastCel:))
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 696)
					(event claimed: 1)
				)
				(verbDo
					(CastleHandsOff)
					(curRoom setScript: gotoHole)
					(event claimed: 1)
				)
			)
		)
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

(instance dieScumScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 30 60)))
			(1
				(CastleHandsOff)
				(ego setMotion: PolyPath 90 160 self)
			)
			(2
				(SpeakAudio 689)
				(= cycles 1)
			)
			(3
				((ego head?) hide:)
				(ego
					setMotion: 0
					normal: 0
					view: 264
					setLoop: 1
					setCycle: EndLoop self
				)
				(= inCartoon 0)
			)
			(4
				(= deathMessage 690)
				(EgoDead)
			)
		)
	)
)

(instance grate of RFeature
	(properties
		nsTop 3
		nsLeft 138
		nsBottom 24
		nsRight 184
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
				(verbLook
					(SpeakAudio 697)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 704)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance ringsScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				;(if (< (DoSound sndDISPOSE) 8)
				(if (< (DoSound 3) 8)
					((ScriptID 550 5) setCycle: EndLoop self)
				else
					(theAudio number: 8071 loop: 1 play: self)
					((ScriptID 550 5) setCycle: EndLoop)
				)
			)
			(1 (= cycles 5))
			(2 (self init:))
		)
	)
)

(instance enterHole of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(Load VIEW 0)
				(theMusic number: 67 loop: -1 playBed:)
				(ego
					init:
					normal: 0
					view: 883
					posn: 33 158
					illegalBits: 0
					ignoreActors: 1
					setLoop: 4
					cel: 0
					setCycle: EndLoop self
				)
				((ego head?) hide:)
			)
			(1
				((ego head?) show:)
				(ego
					view: 0
					setLoop: -1
					setCycle: KQ5SyncWalk
					posn: 31 158
					setMotion: MoveTo (+ (ego x?) 30) (ego y?) self
				)
			)
			(2
				(reflection setPri: 3 init:)
				(drop setScript: drippingWater init: hide:)
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance henchCaught of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theAudio number: 8018 loop: 1 play:)
				((ScriptID 550 4)
					view: 695
					setLoop: 3
					posn: 294 161
					cycleSpeed: 3
					init:
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 550 5)
					init:
					posn: ((ScriptID 550 4) x?) (- ((ScriptID 550 4) y?) 34)
					setLoop: 3
					cycleSpeed: 1
					setPri: 5
					setScript: ringsScript
				)
				((ScriptID 550 3)
					init:
					setScript: 0
					illegalBits: 0
					ignoreActors: 1
					show:
					view: 904
					posn: 334 155
					setPri: 5
					setLoop: 3
					setCel: 0
					cycleSpeed: 4
					moveSpeed: 2
					setMotion: MoveTo 295 155 self
				)
			)
			(2
				((ScriptID 550 3) setCycle: CycleTo 3 1 self)
			)
			(3
				((ScriptID 550 3) cel: 4)
				(ego posn: 227 147 show: setLoop: 14 cel: 1 normal: 0)
				(= cycles 1)
			)
			(4
				((ScriptID 550 3) setCycle: EndLoop)
				(ego cel: 2 setCycle: EndLoop self)
			)
			(5
				(theAudio number: 8078 play:)
				(ego
					view: 882
					setLoop: 11
					posn: (- (ego x?) 13) (+ (ego y?) 3)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(drop setScript: drippingWater init: hide:)
				(NormalEgo)
				(ego view: 0 loop: 2)
				((ego head?) moveHead: 1 show:)
				(reflection setPri: 3 init:)
				((ScriptID 550 3) dispose:)
				(= henchmanState 0)
				(if (== wizardState 5)
					(Bset 64)
					(if (< henchmanTimer 120) (= henchmanTimer 120))
				)
				((ScriptID 550 5) dispose:)
				(theAudio number: 8018 loop: 1 play:)
				((ScriptID 550 4)
					view: 695
					setCel: 255
					cycleSpeed: 4
					setCycle: BegLoop self
				)
			)
			(7
				((ScriptID 550 4) dispose:)
				(= seconds 2)
			)
			(8
				(if
					(and
						(not (Btst 96))
						(== ((inventory at: 25) owner?) 57)
					)
					(stone setScript: moveStone)
				else
					(stone setScript: dieScumScript)
				)
				(theMusic number: 67 loop: -1 playBed:)
				(= seconds 2)
			)
			(9
				(globalCedric setScript: mouseRunning)
				(= seconds 3)
			)
			(10
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance gotoHole of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					ignoreActors: 1
					setPri: (if (< (ego y?) 166) 5 else -1)
					setMotion: PolyPath 60 160 self
				)
			)
			(1
				(ego setPri: 5 setMotion: PolyPath 33 155)
				(client setScript: 0)
			)
		)
	)
)
