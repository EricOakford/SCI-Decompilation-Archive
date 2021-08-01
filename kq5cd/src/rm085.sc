;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Sync)
(use PolyPath)
(use Polygon)
(use Chase)
(use RFeature)
(use Avoider)
(use Motion)
(use Actor)
(use System)

(public
	rm085 0
)

(local
	local0
	[local1 28] = [0 0 319 0 319 183 290 183 248 133 212 133 217 148 145 148 145 140 97 140 97 145 124 145 117 185 0 185]
	[local29 9] = [1003 135 77 4 11 25 23 31 31]
	[local38 9] = [1016 130 64 4 8 26 21 27 24]
)
(instance rm085 of KQ5Room
	(properties
		picture 85
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(addToPics add: smokeOut1 smokeOut2 thug2Bottom doit:)
		(self
			setFeatures: thug2Bottom backRoom room
			addObstacle: poly1
		)
		(switch prevRoomNum
			(28
				(HandsOff)
				(ego view: 0 posn: 55 143 init:)
				(self setScript: getKilled)
			)
			(else 
				(ego view: 0 posn: 165 170 init:)
			)
		)
		(dog init:)
		(smoke1 init:)
		(smoke2 init:)
		(IKHead setScript: headScript init:)
		(thug1 setMotion: 0 setScript: (drinkScript new:) init:)
		(thug2 setScript: (drinkScript new:) init:)
		(IKArm setCycle: Fwd cycleSpeed: 2 init:)
		(innKeeper init:)
		(theMusic number: 119 loop: -1 vol: 127 play:)
		(poly1 points: @local1 size: 14)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(or
						(& (= temp1 (ego onControl: 0)) $0040)
						(< (ego y?) 155)
					)
					(not local0)
				)
				(theMusic number: 120 loop: -1 play:)
				(HandsOff)
				(++ local0)
				(if (not (Btst 39))
					(proc762_1 @local38 5400)
				else
					(proc762_1 @local38 5405)
				)
				(ego setMotion: MoveTo (ego x?) 148)
			)
			((< (ego y?) 150) (HandsOff) (self setScript: attack))
			((& temp1 $4000) (curRoom newRoom: 27))
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(DisposeScript 972)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance getKilled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 158 149 self)
			)
			(1 (client setScript: 0))
		)
	)
)

(instance headScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: End)
				(= seconds (Random 3 8))
			)
			(1
				(client setCycle: Beg)
				(= state -1)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance attack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(IKHead dispose:)
				(IKArm dispose:)
				(thug2 setCycle: Beg setScript: 0)
				(innKeeper
					view: 800
					setScript: 0
					setCycle: Walk
					setLoop: -1
					moveSpeed: 1
					setMotion: MoveTo 78 132 self
					startUpd:
				)
				(ego setMotion: 0)
				(if (not (Btst 39)) (proc762_1 @local29 5401))
			)
			(1
				(innKeeper setMotion: MoveTo 78 142 self)
			)
			(2
				(dog setCycle: End)
				(thug1
					view: 806
					setScript: 0
					illegalBits: 2048
					setCycle: Walk
					ignoreActors: 1
					moveSpeed: 1
					setMotion: MoveTo (+ (ego x?) 20) (ego y?) self
				)
				(innKeeper
					observeControl: 2048
					ignoreActors: 1
					setAvoider: (Avoid new:)
					setMotion: Chase ego 20 self
				)
			)
			(3)
			(4
				(cls)
				(innKeeper setMotion: 0 loop: 5)
				(ego loop: 1)
				(thug1 loop: 7)
				(= cycles 3)
			)
			(5
				(if (not (Btst 39))
					(proc762_1 @local38 5402 self)
				else
					(= cycles 1)
				)
			)
			(6
				(if (not (Btst 39))
					(thugHead
						x: (thug1 x?)
						y: (- (thug1 y?) 40)
						setPri: (+ (thug1 priority?) 1)
						setCycle: MouthSync 5403
						init:
					)
					(SpeakAudio 5403 self)
				else
					(= cycles 1)
				)
			)
			(7
				(thugHead setCycle: 0 dispose:)
				(innHead
					x: (innKeeper x?)
					y: (innKeeper y?)
					setPri: (+ (innKeeper priority?) 1)
					init:
				)
				(if (not (Btst 39))
					(innHead setCycle: MouthSync 5404)
					(SpeakAudio 5404 self)
				else
					(innHead setCycle: MouthSync 5406)
					(SpeakAudio 5406 self)
				)
			)
			(8
				(innHead setCycle: 0 dispose:)
				(cls)
				(DoAudio 1 8023)
				(thug1 view: 810 loop: 0 cel: 0 setCycle: End self)
			)
			(9
				(theAudio number: 8023 loop: 1 play:)
				(= cycles 4)
			)
			(10
				(theAudio number: 8121 loop: 1 play:)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 810
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(11
				(theMusic stop:)
				(theMusic2 stop:)
				(if (not (Btst 39))
					(Graph grFILL_BOX 0 0 200 320 3 0 0)
					(Graph grUPDATE_BOX 0 0 200 320 1)
					(cast eachElementDo: #dispose)
					(Bset 39)
					(curRoom newRoom: 86)
				else
					(= deathMessage 719)
					(EgoDead)
				)
			)
		)
	)
)

(instance drinkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 8)))
			(1
				(client setCycle: CT 3 1)
				(= seconds 3)
			)
			(2
				(client setCycle: End self)
				(if (< (Random 1 100) 25) (= state -1))
			)
			(3
				(client setCycle: CT 1 1)
				(= seconds 1)
			)
			(4
				(client setCycle: CT 0 -1)
				(= seconds 1)
				(if (< (Random 1 100) 25)
					(= state -1)
				else
					(= state 2)
				)
			)
		)
	)
)

(instance IKArm of Prop
	(properties
		x 159
		y 98
		view 802
		loop 1
		priority 10
		signal $0010
	)
)

(instance IKHead of Prop
	(properties
		x 159
		y 88
		view 802
		loop 2
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 720)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 724)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance innHead of Prop
	(properties
		view 800
		loop 6
		cycleSpeed 4
	)
)

(instance innKeeper of Actor
	(properties
		x 160
		y 129
		view 802
		signal $0001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 720)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 724)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance thugHead of Prop
	(properties
		view 806
		loop 6
		cycleSpeed 4
	)
)

(instance thug1 of Actor
	(properties
		x 170
		y 141
		view 808
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 720)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 724)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance thug2Bottom of RPicView
	(properties
		x 150
		y 141
		view 814
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 720)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 724)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance thug2 of Prop
	(properties
		x 145
		y 114
		view 814
		loop 1
		priority 10
		signal $4010
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 720)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 724)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance dog of Prop
	(properties
		x 233
		y 128
		view 798
		signal $0001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 721)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 725)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 727)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance backRoom of RFeature
	(properties
		nsTop 69
		nsLeft 215
		nsBottom 122
		nsRight 251
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 722)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 726)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 723)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(10
							(SpeakAudio 728)
							(++ local0)
							(ego setMotion: MoveTo (ego x?) 145)
						)
					)
				)
				(JOY_DOWN
					(HandsOff)
					(theMusic number: 120 loop: -1 play:)
					(++ local0)
					(ego setMotion: MoveTo (ego x?) 145)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance smoke1 of View
	(properties
		x 187
		y 3
		view 798
		loop 3
	)
)

(instance smoke2 of View
	(properties
		x 294
		y 7
		view 798
		loop 3
		cel 1
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance smokeOut1 of PicView
	(properties
		x 187
		y 3
		view 292
		loop 3
	)
)

(instance smokeOut2 of PicView
	(properties
		x 294
		y 7
		view 292
		loop 4
	)
)
