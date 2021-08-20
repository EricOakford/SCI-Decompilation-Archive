;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include game.sh)
(use Main)
(use DLetter)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm216 0
)

(local
	local0
	girlCued
	local2
	pts0 = [
		73 174
		25 174
		25 162
		31 157
		49 154
		67 157
		73 162
		97 166
		97 172
		]
	pts1 = [
		0 0
		319 0
		319 102
		272 102
		307 122
		262 143
		201 147
		193 134
		274 119
		187 108
		185 128
		153 129
		145 122
		111 125
		107 119
		148 113
		143 107
		119 112
		24 100
		0 87
		]
	pts2 = [
		233 159
		246 155
		260 159
		245 163
		]
	pts3 = [
		105 150
		118 145
		129 150
		117 154
		]
	pts4 = [0 112 36 111 46 116 39 119 0 120]
	pts5 = [63 115 76 115 100 124 111 142 90 142 61 126]
	pts6 = [37 122 52 116 98 125 105 143 43 143]
	local109 =  1
	local110
	local111
	local112
	saveSortedFeatures
)
(instance rm216 of KQ5Room
	(properties
		picture 216
		north 15
		east 15
		south 15
		west 15
	)
	
	(method (init)
		(super init:)
		(= saveSortedFeatures useSortedFeatures)
		(= useSortedFeatures TRUE)
		(self addObstacle: poly0 poly1 poly2 poly3 poly4)
		(self setFeatures: fireRing)
		(addToPics add: fireRing doit:)
		(if (Btst fSawBandits)
			(self
				setFeatures: horse1 horse2 horse3 dancer camel littleTent bigTent
			)
			(theMusic number: 46 loop: -1 playBed:)
			(addToPics add: horse1 horse2 horse3 camel doit:)
			(if (== (theGame detailLevel:) 3)
				(crowd1
					init:
					cycleSpeed: 2
					setScript: (crowdScript new:)
				)
				(crowd2
					init:
					cycleSpeed: 2
					setScript: (crowdScript new:)
				)
				(girl init: setScript: girlScript)
			else
				(crowd1 init: stopUpd:)
				(crowd2 init: stopUpd:)
				(girl init: stopUpd:)
			)
			(if (not (Btst fSawDrunkGuy))
				(HandsOff)
				(Bset fSawDrunkGuy)
				(drunkGuy init: setScript: drunkGuyScript)
			else
				(drunkGuy
					loop: 2
					posn: 60 129
					cel: (- (NumCels drunkGuy) 1)
					init:
					stopUpd:
				)
			)
			(self addObstacle: poly6)
			(fire loop: 2)
		else
			(theMusic number: 44 loop: -1 play: 90)
			(theMusic2 number: 2 loop: -1 play:)
			(theMusic3 number: 3 loop: -1 play:)
			(self addObstacle: poly5)
			(fire loop: 1)
		)
		(fire init: cycleSpeed: 3 setCycle: Forward)
		(waterJug init: stopUpd:)
		(switch prevRoomNum
			(17 (ego posn: 175 142))
			(else 
				(ego
					x: (if (> (ego x?) 100) 309 else 10)
					y: (if (< (ego y?) 150) 185 else 165)
				)
			)
		)
		(ego
			view: 0
			illegalBits: cWHITE
			setPri: -1
			setStep: 3 2
			init:
		)
		(if (not (Btst fFoundCamp))
			(Bset fFoundCamp)
			(SolvePuzzle 3)
		)
		(self setFeatures: littleTent bigTent)
		(poly0 points: @pts0 size: 9)
		(poly1 points: @pts1 size: 20)
		(poly2 points: @pts2 size: 4)
		(poly3 points: @pts3 size: 4)
		(poly4 points: @pts4 size: 5)
		(poly5 points: @pts5 size: 6)
		(poly6 points: @pts6 size: 5)
	)
	
	(method (doit &tmp edge)
		(cond 
			(script (script doit:))
			(local112
				(if (ego mover?)
					(= local112 0)
					(ego show:)
					((ego head?) show:)
					(waterJug cel: 0 stopUpd:)
				)
			)
			((= edge (self edgeToRoom: (ego edgeHit?)))
				(switch (ego edgeHit?)
					(NORTH
						(-- desertRoomY)
					)
					(SOUTH
						(= desertRoomX 10)
						(= desertRoomY 8)
					)
					(EAST
						(= desertRoomX 9)
						(= desertRoomY 7)
					)
					(WEST
						(= desertRoomX 11)
						(= desertRoomY 7)
					)
				)
				(curRoom newRoom: edge)
			)
			((& (ego onControl: 0) cYELLOW)
				(curRoom newRoom: 17)
			)
			((& (ego onControl: 0) cRED)
				(curRoom setScript: killEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else
				(switch (event message?)
					(verbTalk
						(SpeakAudio 9059)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(theMusic2 fade:)
		(theMusic fade:)
		(theMusic3 fade:)
		(= useSortedFeatures saveSortedFeatures)
		(super newRoom: n)
	)
)

(instance searchDrunk of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 70 143 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 56
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2 (= cycles 15))
			(3 (= cycles 15))
			(4 (ego setCycle: BegLoop self))
			(5
				(SpeakAudio 799)
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					loop: 7
					cel: 1
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(6
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance drunkGuyScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(drunkGuy setLoop: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(drunkGuy
					loop: 1
					posn: (+ (drunkGuy x?) 4) (+ (drunkGuy y?) 1)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(drunkGuy
					loop: 2
					posn: (- (drunkGuy x?) 2) (+ (drunkGuy y?) 5)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(HandsOn)
				(drunkGuy setScript: 0 ignoreActors: stopUpd:)
			)
		)
	)
)

(instance killEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego signal: ignrAct setMotion: MoveTo 81 110 self)
			)
			(1
				(theMusic number: 129 priority: 500 loop: 1 play:)
				(SpeakAudio 5902 0 1)
				(crowd1 setScript: 0 setCycle: 0)
				(crowd2 setScript: 0 setCycle: 0)
				(girl setScript: 0 setCycle: 0)
				(killingBandit init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(ego hide:)
				((ego head?) hide:)
				(killingBandit
					posn: (+ (killingBandit x?) 9) (+ (killingBandit y?) 1)
					loop: 1
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(3
				(killingBandit loop: 2 cel: 0)
				(ego
					show:
					normal: 0
					view: 426
					y: (+ (ego y?) 7)
					loop: 3
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(4
				(cls)
				(= seconds 2)
			)
			(5
				(= deathMessage 788)
				(EgoDead)
			)
		)
	)
)

(instance girlScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if girlCued
					(= cycles 1)
				else
					(= cycles 15)
				)
			)
			(1
				(girl view: 385 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(girl setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(girl setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(4
				(girl setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(5
				(girl view: 387 cel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(6
				(girl setLoop: 2 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(7
				(girl setLoop: 4 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(8
				(girl view: 389 cel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(9
				(girl cel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(10
				(girl cel: 0 setLoop: 4 setCycle: EndLoop self)
			)
			(11
				(girl cel: 0 setLoop: 6 setCycle: EndLoop self)
			)
			(12
				(girl cel: 0 setLoop: 8 setCycle: EndLoop self)
			)
			(13
				(girl view: 385 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(14
				(girl setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(15
				(girl setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(16
				(girl setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(17
				(girl view: 387 cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(18
				(girl setLoop: 3 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(19
				(girl setLoop: 5 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(20
				(girl view: 389 cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(21
				(girl cel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(22
				(girl cel: 0 setLoop: 5 setCycle: EndLoop self)
			)
			(23
				(girl cel: 0 setLoop: 7 setCycle: EndLoop self)
			)
			(24
				(girl cel: 0 setLoop: 9 setCycle: EndLoop self)
			)
			(25
				(++ girlCued)
				(self init:)
			)
		)
	)
)

(instance crowdScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(client cel: 0)
				(= state -1)
				(= seconds (Random 4 8))
			)
		)
	)
)

(instance walkToJug of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (!= (ego x?) 84) (!= (ego y?) 168))
					(ego
						ignoreActors: TRUE
						illegalBits: 0
						setMotion: PolyPath 84 168 self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if (== ((theIconBar curIcon?) message?) verbDo)
					(waterJug cycleSpeed: 12)
					(SpeakAudio 311)
					(= globalCedric 0)
					(= local110 1)
					(= cycles 1)
				else
					(ego loop: 2)
					(RedrawCast)
					(= cycles 2)
				)
			)
			(2
				(if local110
					(ego hide:)
					((ego head?) hide:)
					(waterJug cel: 1 setCycle: EndLoop self)
				else
					(SpeakAudio 789)
					(= cycles 1)
				)
			)
			(3
				(= cycles 1)
				(if local110
					(+= cycles 16)
				)
			)
			(4
				(if local110
					(waterJug setCycle: CycleTo 2 -1 self)
				else
					(= cycles 1)
				)
			)
			(5
				(= cycles 1)
				(if local110 (= cycles (+ cycles 4)))
			)
			(6
				(if (and local110 (< local110 3))
					(waterJug setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(7
				(cond 
					((and local109 (!= local110 0) (< local110 3))
						(++ local110)
						(-= state 5)
					)
					(local110
						(waterJug cel: 1)
						(= local109 (= local110 0))
						(= local112 1)
						(cls)
					)
				)
				(= cycles 1)
			)
			(8
				(HandsOn)
				(= local111 1)
				(client setScript: 0)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 49
		y 173
		z 7
		view 416
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fSawBandits)
					(SpeakAudio 790)
				else
					(SpeakAudio 791)
				)
			)
			(verbDo
				(if (Btst fSawBandits)
					(SpeakAudio 801)
				else
					(SpeakAudio 802)
				)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance waterJug of Prop
	(properties
		x 81
		y 169
		view 416
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 789)
			)
			(verbDo
				(if local109
					(curRoom setScript: walkToJug)
				else
					(SpeakAudio 800)
				)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance crowd1 of Prop
	(properties
		x 66
		y 93
		onMeCheck cBLUE
		view 420
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 794)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance crowd2 of Prop
	(properties
		x 158
		y 67
		onMeCheck cBLUE
		view 420
		loop 1
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 794)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance fireRing of RPicView
	(properties
		x 49
		y 172
		view 416
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fSawBandits)
					(SpeakAudio 790)
				else
					(SpeakAudio 791)
				)
			)
			(verbDo
				(if (Btst fSawBandits)
					(SpeakAudio 801)
				else
					(SpeakAudio 802)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance horse1 of RPicView
	(properties
		x 282
		y 58
		view 422
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 792)
			)
			(verbDo
				(if (not local0)
					(++ local0)
					(SpeakAudio 803)
				)
			)
			(verbUse
				(switch (inventory indexOf: (theIconBar curInvIcon?))
					(iWand 0)
					(else
						(SpeakAudio 805)
					)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance horse2 of RPicView
	(properties
		x 296
		y 70
		view 422
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 792)
			)
			(verbDo
				(SpeakAudio 803)
			)
			(verbUse
				(switch (inventory indexOf: (theIconBar curInvIcon?))
					(iWand 0)
					(else
						(SpeakAudio 805)
					)
				)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance horse3 of RPicView
	(properties
		x 305
		y 88
		view 422
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 792)
			)
			(verbDo
				(SpeakAudio 803)
			)
			(verbUse
				(switch (inventory indexOf: (theIconBar curInvIcon?))
					(iWand 0)
					(else
						(SpeakAudio 805)
					)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance camel of RPicView
	(properties
		x 166
		y 110
		view 422
		loop 3
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 793)
			)
			(verbDo
				(SpeakAudio 804)
			)
			(verbUse
				(switch (inventory indexOf: (theIconBar curInvIcon?))
					(iWand 0)
					(else
						(SpeakAudio 806)
					)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance bigTent of RFeature
	(properties
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fSawBandits)
					(SpeakAudio 794)
				else
					(SpeakAudio 795)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance littleTent of RFeature
	(properties
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 796)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance dancer of RFeature
	(properties
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 797)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance girl of Prop
	(properties
		x 176
		y 84
		view 385
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 3
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SpeakAudio 797)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
	
	(method (checkDetail param1)
		(super checkDetail: &rest)
		(cond 
			((not detailLevel))
			((& signal stopUpdOn)
				(|= signal staticView)
			)
			((& signal startUpdOn)
				(&= signal (~ staticView))
			)
		)
	)
)

(instance drunkGuy of Prop
	(properties
		x 58
		y 123
		view 421
		cycleSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (self script?)
					(KQPrint 216 0)
				else
					(SpeakAudio 798)
				)
			)
			(verbDo
				(if (not local2)
					(HandsOff)
					(= local2 1)
					(self setScript: searchDrunk)
				)
			)
			(else
				(super doVerb: &rest)
			)
		)
	)
)

(instance killingBandit of Actor
	(properties
		x 73
		y 117
		view 426
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance poly0 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly3 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly4 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly5 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly6 of Polygon
	(properties
		type PBarredAccess
	)
)
