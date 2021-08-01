;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm021 0
)

(local
	local0
	local1
	local2
	[local3 28] = [0 130 220 130 239 152 210 166 139 166 116 153 167 139 187 132 54 140 81 146 72 168 15 164 81 189 0 189]
	[local31 24] = [0 0 319 0 319 189 190 189 190 186 303 186 303 155 263 155 242 134 251 121 221 111 0 111]
	[local55 68] = [0 189 0 0 319 0 319 189 194 189 308 179 268 153 233 107 215 107 223 132 241 155 213 165 178 164 141 163 126 158 140 142 193 130 202 110 157 84 121 84 104 111 139 106 148 91 179 107 183 122 156 130 70 139 41 135 40 139 78 147 72 153 77 160 55 177 94 189]
)
(instance rm021 of KQ5Room
	(properties
		picture 21
		south 20
		west 22
	)
	
	(method (init)
		(super init:)
		(ego init: view: 0 setStep: 3 2)
		(switch prevRoomNum
			(south
				(ego posn: 142 187)
				(self obstacles: polyListFront)
			)
			(west
				(ego setPri: 8 posn: 5 129)
				(self obstacles: polyListBack)
				(= local0 1)
			)
			(208
				(ego posn: gGEgoX gGEgoY view: 0)
				(NormalEgo 0 0)
				(self obstacles: polyListFront)
			)
			(else 
				(ego posn: 160 175 view: 0)
				(NormalEgo 0 0)
				(self obstacles: polyListFront)
			)
		)
		(Load rsVIEW 100)
		(door init: stopUpd:)
		(if (Btst 73) (door hide:))
		(self setFeatures: path21 tree setRegions: 200 551)
		(poly1 points: @local3 size: 14)
		(poly2 points: @local31 size: 12)
		(poly3 points: @local55 size: 33)
		(polyListBack add: poly1 poly2)
		(polyListFront add: poly3)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			((== script falling) (script doit:))
			((& (= temp1 (ego onControl: 0)) $0200)
				(ego setPri: 8)
				(curRoom obstacles: polyListBack)
				(= local0 1)
			)
			((& temp1 $0400)
				(ego setPri: -1)
				(curRoom obstacles: polyListFront)
				(= local0 0)
			)
			((and (not local0) (& temp1 $0002)) (ego setPri: 10))
			((and (not local0) (& temp1 $0010)) (ego setPri: -1))
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(ego setPri: -1)
				((ScriptID 200 1) register: temp0)
				(self setScript: (ScriptID 200 1) 0 (ego edgeHit?))
			)
			((and (not local0) (& temp1 $0080)) (HandsOff) (self setScript: falling))
		)
	)
	
	(method (dispose)
		(polyListFront dispose:)
		(polyListBack dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance openDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0
					(ego setMotion: PolyPath 215 163 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 131 84 self)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 430
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(3
				(if register
					(SpeakAudio 339)
					(= cycles 1)
				else
					(SpeakAudio 341 self)
				)
			)
			(4
				(if register
					(= cycles 1)
				else
					(Bset 73)
					(door hide:)
					(ego loop: 1 cel: 0 setCycle: End self)
					(theAudio number: 8122 loop: 1 play:)
				)
			)
			(5
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					loop: -1
					setCycle: KQ5SyncWalk
				)
				(if (not register) (heart init:) (SpeakAudio 342))
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getHeart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0
					(ego setMotion: PolyPath 215 163 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 131 84 self)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 430
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(theMusic number: 36 loop: 1 play:)
			)
			(3
				(SpeakAudio 340)
				(ego loop: 1 cel: 3 setCycle: Beg self)
				(theAudio number: 8124 loop: 1 play:)
			)
			(4
				(theMusic number: 4 loop: -1 play:)
				(Bclr 73)
				(door show:)
				(ego
					normal: 1
					view: 0
					loop: 3
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					get: 9
				)
				(SolvePuzzle 2)
				((ego head?) show:)
				(heart dispose:)
				(= cycles 1)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					view: 100
					normal: 0
					illegalBits: 0
					setLoop: 1
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(1
				(ego
					yStep: 10
					setMotion: MoveTo (ego x?) (+ (ego y?) 30) self
				)
			)
			(2
				(ego cel: 3)
				(theAudio number: 8078 loop: 1 play:)
				(= seconds 1)
			)
			(3
				(ego
					setLoop: 3
					x: (- (ego x?) 14)
					y: (+ (ego y?) 7)
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(4
				(ego
					setLoop: 7
					x: (+ (ego x?) 4)
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(5
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					setPri: -1
					loop: 3
					setCycle: KQ5SyncWalk
					yStep: 2
					cycleSpeed: 0
					illegalBits: -32768
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(6
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance path21 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 345)
					(event claimed: 1)
				)
			)
		)
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
					(SpeakAudio 346)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 121
		y 64
		view 428
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
					(if (== ((inventory at: 9) owner?) 21)
						(SpeakAudio 347)
					else
						(SpeakAudio 348)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cond 
						((!= ((inventory at: 9) owner?) 21) (SpeakAudio 348))
						((Btst 73) (openDoor register: 0) (curRoom setScript: getHeart))
						(else
							(openDoor register: 1)
							(HandsOff)
							(curRoom setScript: openDoor)
						)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(1
							(cond 
								((!= ((inventory at: 9) owner?) 21) (SpeakAudio 348))
								((Btst 73) (SpeakAudio 343))
								(else
									(++ local1)
									(HandsOff)
									(openDoor register: 0)
									(curRoom setScript: openDoor)
									(SolvePuzzle 3)
								)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 344)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance heart of Prop
	(properties
		x 42
		y 76
		view 428
		loop 1
		cel 1
		priority 15
		signal $0011
	)
	
	(method (doit)
		(super doit:)
		(if (ego mover?) (self dispose:))
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
					(SpeakAudio 342)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getHeart)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance polyListBack of List
	(properties)
)

(instance polyListFront of List
	(properties)
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
