;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm051 0
)

(local
	local0
	[local1 2]
	local3
	[local4 22] = [0 189 0 130 130 130 120 159 72 156 104 160 118 163 114 168 88 168 92 175 71 189]
	[local26 8] = [0 85 148 92 135 127 0 128]
	[local34 10] = [0 80 118 70 139 76 147 89 0 82]
	[local44 14] = [0 31 193 45 205 49 194 56 143 64 105 69 0 76]
	[local58 20] = [231 189 240 178 255 178 253 170 240 162 188 161 170 156 174 121 319 121 319 189]
	[local78 8] = [106 180 130 172 154 177 138 184]
	[local86 8] = [173 164 205 164 206 179 175 183]
	[local94 16] = [319 118 172 118 169 83 150 74 240 49 0 14 0 0 319]
	[local110 6] = [0 3082 1 7031]
	[local116 9] = [1000 153 115 4 11 24 19 23 30]
	[local125 9] = [1003 117 95 4 11 25 23 31 31]
)
(instance rm051 of KQ5Room
	(properties
		picture 51
		north 52
		south 89
	)
	
	(method (init)
		(Load SCRIPT 941)
		(if (== (theGame detailLevel:) 3)
			(surf1 setScript: waves)
		)
		(if (not (Btst 55))
			(= cedricX 186)
			(= cedricY 176)
			(= global325 3070)
		)
		(HandsOn)
		(if (== ((inventory at: 37) owner?) 51)
			(fishy init: stopUpd: ignoreActors: 1)
		)
		(switch prevRoomNum
			(north
				(ego view: 0 init: posn: 222 51 setPri: 15 setStep: 3 2)
				(if (not (Btst 55))
					(self setRegions: 202)
					(self setScript: (ScriptID 202 1))
					(globalCedric setPri: 14)
				else
					(ego setMotion: MoveTo 222 51)
				)
			)
			(else 
				(ego
					view: 664
					loop: 1
					cel: 0
					posn: 123 171
					setPri: -1
					setStep: 3 2
				)
				(ego normal: 0 init:)
				(if (not (Btst 55))
					(Cedric init: stopUpd:)
					(board init: stopUpd:)
				)
				(if (Btst 112)
					(setUpCode doit:)
					(Bclr 112)
				else
					((ego head?) view: 0 hide:)
					(self setScript: marooned)
				)
			)
		)
		(poly1 points: @local4 size: 11)
		(poly2 points: @local26 size: 4)
		(poly3 points: @local34 size: 5)
		(poly4 points: @local44 size: 7)
		(poly5 points: @local58 size: 10)
		(poly6 points: @local78 size: 4)
		(poly7 points: @local86 size: 4)
		(poly8 points: @local94 size: 8)
		(sailBoat init: stopUpd:)
		(rubble init:)
		(mast setPri: 11 init: stopUpd:)
		(theMusic number: 831 loop: -1 vol: 127 play:)
		(self
			setFeatures: path51 beach
			addObstacle: poly1 poly2 poly3 poly4 poly5 poly6 poly7 poly8
		)
		(super init:)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			((ego inRect: 126 87 144 95)
				(ego priority: 11 signal: (| (ego signal?) $0010))
				((ego head?)
					priority: 11
					signal: (| ((ego head?) signal?) $0010)
				)
			)
			((== (curRoom script?) falling))
			((ego inRect: 46 129 125 162)
				(ego priority: 12 signal: (| (ego signal?) $0010))
				((ego head?)
					priority: 12
					signal: (| ((ego head?) signal?) $0010)
				)
			)
			(
				(and
					(& (= temp1 (ego onControl: 1)) $0004)
					(== (ego view?) 0)
				)
				(ego view: 26)
			)
			(
			(and (not (& temp1 $0004)) (== (ego view?) 26)) (ego view: 0))
			((ego inRect: 90 50 180 80)
				(ego priority: 5 signal: (| (ego signal?) $0010))
				((ego head?)
					priority: 5
					signal: (| ((ego head?) signal?) $0010)
				)
			)
			((< (ego y?) 67)
				(ego priority: 6 signal: (| (ego signal?) $0010))
				((ego head?)
					priority: 6
					signal: (| ((ego head?) signal?) $0010)
				)
			)
			((> (ego y?) 67)
				(ego priority: -1 signal: (& (ego signal?) $ffef))
				((ego head?)
					priority: -1
					signal: (& ((ego head?) signal?) $ffef)
				)
			)
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(HandsOff)
				(curRoom setScript: leaveSouth)
			)
			((& (ego onControl: 0) $4000) (HandsOff) (self setScript: leave))
			((& (ego onControl: 1) $3400) (self setScript: falling))
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
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
		(theMusic fade:)
	)
)

(instance leaveSouth of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (cast contains: globalCedric)
					(proc762_1 @local116 9108)
				)
				(= cycles 1)
			)
			(1
				(ego setPri: -1 setMotion: PolyPath (ego x?) 220 self)
			)
			(2 (curRoom newRoom: 89))
		)
	)
)

(instance leave of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (- (ego x?) 10) 36 self)
			)
			(1
				(if (not (cast contains: globalCedric))
					(curRoom newRoom: 52)
				else
					(globalCedric setScript: 0)
					(if (cast contains: globalCedric)
						(globalCedric view: 138 loop: 5 setCycle: EndLoop self)
					)
				)
			)
			(2
				(globalCedric loop: 9 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 52)
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
					setCycle: EndLoop self
					setPri: 11
					ignoreActors:
					cycleSpeed: 10
				)
				(surf2
					init:
					setCycle: RandCycle
					setPri: 11
					ignoreActors:
					cycleSpeed: 10
				)
				(surf3
					init:
					setCycle: RandCycle
					setPri: 11
					ignoreActors:
					cycleSpeed: 10
				)
				(surf4
					init:
					setCycle: RandCycle
					setPri: 11
					ignoreActors:
					cycleSpeed: 10
				)
			)
			(1
				(surf1 setCycle: BegLoop self)
				(surf2 setCycle: BegLoop)
				(surf3 setCycle: BegLoop)
				(surf4 setCycle: BegLoop)
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance marooned of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 5))
			(1
				(ego loop: 1 cycleSpeed: 2 setCycle: Forward)
				(= cycles 15)
			)
			(2
				(ego loop: 2 setCycle: EndLoop self)
			)
			(3
				(if (not (Btst 55))
					(proc762_1 @local116 3079 self)
					(Cedric
						cycleSpeed: 3
						ignoreActors: 1
						setPri: -1
						illegalBits: 0
						setCycle: Forward
					)
				else
					(ego view: 0 loop: 3 init:)
					((ego head?) show:)
					(NormalEgo)
					(HandsOn)
					(client setScript: 0)
				)
			)
			(4
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					cycleSpeed: 0
					illegalBits: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 162 173 self
				)
			)
			(5
				(cls)
				(Cedric hide:)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 664
					posn: 186 176
					loop: 9
					setPri: -1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(6
				(= [local125 1] (Min (Max 0 (- (ego x?) 40)) 239))
				(proc762_1 @local125 7030)
				(ego
					normal: 1
					view: 0
					posn: 162 173
					loop: 2
					cel: 0
					illegalBits: -32768
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(Cedric show: loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(proc762_1 @local116 3080)
				(Cedric loop: 11 cel: 0 setPri: 14 setCycle: EndLoop self)
			)
			(8
				(proc762_1 @local116 3081 self)
				(Cedric ignoreActors: 0 loop: 4 cel: 5 stopUpd:)
			)
			(9
				(cls)
				(setUpCode doit:)
				(client setScript: 0)
			)
		)
	)
)

(instance setUpCode of Code
	(properties)
	
	(method (doit)
		(cls)
		(if (not (Btst 55))
			(Cedric hide:)
			(curRoom setRegions: 202)
		)
		((ego head?) view: 0 moveHead: 1 loop: 4 setLoop: -1)
		(ego
			normal: 1
			view: 0
			posn: 162 173
			loop: 2
			cel: 0
			illegalBits: -32768
			cycleSpeed: 0
			setCycle: KQ5SyncWalk
		)
		(ego setLoop: -1 normal: 1)
		(SolvePuzzle 3)
		(HandsOn)
	)
)

(instance falling of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(> (ego y?) 85)
				(!= (ego view?) 0)
				(> (ego cel?) 2)
				(< (ego loop?) 2)
			)
			(ego setPri: 2)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego normal: 0)
				(if (cast contains: globalCedric)
					(proc762_1 @local116 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(cond 
					((& (ego onControl: 1) $1000) (= register 0))
					((& (ego onControl: 1) $2000) (= register 1))
					((& (ego onControl: 1) $0400)
						(if (ego inRect: 130 45 190 65)
							(= register 0)
						else
							(= register 1)
						)
					)
				)
				(= cycles 1)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 68
					setLoop: (if register 0 else 1)
					cel: 0
					setStep: 4 8
					cycleSpeed: 1
					setCycle: EndLoop self
					setPri:
						(cond 
							((ego inRect: 130 45 190 65) 2)
							((< (ego y?) 45) 1)
							((< (ego y?) 88) 2)
							(else 11)
						)
					illegalBits: 0
				)
				(if (== register 0)
					(ego x: (- (ego x?) 10))
				else
					(ego x: (+ (ego x?) 10))
				)
				(theAudio number: 7053 play:)
			)
			(3
				(ego setMotion: MoveTo (ego x?) 105 self)
			)
			(4
				(theAudio number: 8892 loop: 1 play:)
				(ego loop: (+ (ego loop?) 2))
				(= seconds 3)
			)
			(5
				(= deathMessage 412)
				(EgoDead)
			)
			(6
				(HandsOn)
				(ego posn: 160 160 view: 0)
				(NormalEgo)
				(client setScript: 0)
			)
		)
	)
)

(instance getFish of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 113 159 self)
			)
			(1
				(= [local125 1] (Min (Max 0 (- (ego x?) 40)) 239))
				(proc762_1 @local125 7059 self)
			)
			(2
				((ego head?) hide:)
				(ego
					view: 56
					loop: 0
					ignoreActors:
					normal: 0
					setCycle: EndLoop self
				)
			)
			(3
				(SolvePuzzle 2)
				(ego get: 37)
				(fishy dispose:)
				(= cycles 1)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(ego view: 0 setPri: -1 normal: 1)
				((ego head?) show:)
				(NormalEgo)
				(= cycles 1)
			)
			(6
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance island of RFeature
	(properties)
	
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
					(SpeakAudio 546)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path51 of RFeature
	(properties)
	
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
				(verbLook
					(SpeakAudio 547)
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
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0004))
					(not (& (OnControl 4 (event x?) (event y?)) $0010))
				)
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 548)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance board of View
	(properties
		x 186
		y 176
		view 664
		loop 10
		signal $4000
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
					(SpeakAudio 549)
					(event claimed: 1)
				)
				(verbDo
					(if (and (not (Btst 55)) (not local0))
						(++ local0)
						(SpeakAudio 552)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance sailBoat of View
	(properties
		x 62
		y 180
		view 664
		cel 2
		signal $4000
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
					(SpeakAudio 549)
					(event claimed: 1)
				)
				(verbDo
					(if (and (not (Btst 55)) (not local0))
						(++ local0)
						(SpeakAudio 552)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance mast of View
	(properties
		x 244
		y 182
		view 664
		cel 4
		signal $4000
	)
	
	(method (handleEvent event)
		(if (cast contains: globalCedric)
			(globalCedric handleEvent: event)
		)
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
					(SpeakAudio 549)
					(event claimed: 1)
				)
				(verbDo
					(if (and (not (Btst 55)) (not local0))
						(++ local0)
						(SpeakAudio 552)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance rubble of RPicView
	(properties
		x 76
		y 163
		view 664
		cel 3
		signal $4010
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
					(SpeakAudio 549)
					(event claimed: 1)
				)
				(verbDo
					(if (and (not (Btst 55)) (not local0))
						(++ local0)
						(SpeakAudio 552)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance Cedric of Actor
	(properties
		x 186
		y 176
		view 664
		loop 3
		signal $4000
		detailLevel 3
	)
	
	(method (handleEvent event &tmp temp0)
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
					(SpeakAudio 550)
					(event claimed: 1)
				)
				(verbTalk
					(if (not local3)
						(proc762_0 @local116 @local125 @local110)
						(= local3 1)
						(event claimed: 1)
					else
						(event claimed: 0)
					)
				)
			)
		)
	)
)

(instance fishy of Prop
	(properties
		x 108
		y 154
		view 664
		loop 10
		cel 1
		detailLevel 3
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
					(SpeakAudio 551)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: getFish)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wave1 of Prop
	(properties
		x 44
		y 169
		view 664
		loop 5
		detailLevel 3
	)
)

(instance wave2 of Prop
	(properties
		x 32
		y 188
		view 664
		loop 6
		detailLevel 3
	)
)

(instance wave3 of Prop
	(properties
		x 124
		y 188
		view 664
		loop 7
		detailLevel 3
	)
)

(instance wave4 of Prop
	(properties
		x 255
		y 188
		view 664
		loop 8
		detailLevel 3
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties)
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

(instance poly8 of Polygon
	(properties)
)

(instance surf1 of Prop
	(properties
		x 34
		y 189
		view 664
		loop 5
		detailLevel 3
	)
)

(instance surf2 of Prop
	(properties
		x 112
		y 189
		view 664
		loop 6
		detailLevel 3
	)
)

(instance surf3 of Prop
	(properties
		x 226
		y 189
		view 664
		loop 6
		detailLevel 3
	)
)

(instance surf4 of Prop
	(properties
		x 292
		y 189
		view 664
		loop 6
		detailLevel 3
	)
)
