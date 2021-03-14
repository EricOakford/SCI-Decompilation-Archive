;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include game.sh) (include "350.shm")
(use Main)
(use TellerIcon)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm350 0
	sekhmetTalker 1
)

(local
	local0 = [0 -12 -13 999]
	local4 = [0 -14 -15 -16 -17 -18 999]
	[local11 5]
	[local16 7]
	priestessCued
	local24
	local25
	local26
	local27
	toX
	toY
	theGameTime
	flameCel
	local32
)
(instance rm350 of Room
	(properties
		noun N_ROOM
		picture 350
		vanishingY -35
	)
	
	(method (init)
		(cSound number: 350 setLoop: -1 play: 100)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						0 189
						180 189
						142 159
						113 164
						86 142
						106 139
						48 90
						61 90
						53 80
						35 80
						35 53
						25 53
						25 82
						0 82
					yourself:
				)
		)
		(if (ego has: iGem)
			(ego addHonor: 20 solvePuzzle: fBringGemToTemple 10)
		)
		(ego
			view: 0
			setLoop: 2
			x: 27
			y: 92
			noun: N_EGO_TELL
			xStep: 2
			yStep: 1
			setScale: 220
			init:
			changeGait: MOVE_WALK
		)
		(flame1 setCycle: Forward init:)
		(flame2 setCycle: Forward init:)
		(stairs setPri: 10 init: stopUpd:)
		(if Night
			(nPal init:)
			(stairs x: 31 y: 98 cel: 0 setLoop: 3 stopUpd:)
		)
		((Prop new:)
			view: 350
			loop: 4
			cel: 0
			x: 248
			y: 108
			detailLevel: 3
			priority: 2
			signal: fixPriOn
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 5
			cel: 0
			x: 236
			y: 56
			detailLevel: 3
			signal: (| ignrAct fixPriOn)
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 6
			cel: 1
			x: 155
			y: 96
			detailLevel: 3
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 7
			cel: 1
			x: 230
			y: 126
			detailLevel: 3
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 8
			cel: 1
			x: 131
			y: 117
			detailLevel: 3
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 9
			cel: 1
			x: 185
			y: 145
			detailLevel: 3
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 10
			cel: 1
			x: 80
			y: 78
			priority: 5
			signal: fixPriOn
			detailLevel: 3
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		((Prop new:)
			view: 350
			loop: 11
			cel: 1
			x: 159
			y: 76
			detailLevel: 3
			priority: 4
			signal: (| ignrAct fixPriOn)
			setCycle: Forward
			noun: N_STATUE
			init:
		)
		(super init: &rest)
		(cond 
			((Btst fSoulJudged)
				(acolyte1 init: stopUpd:)
				(self setScript: letSGo)
			)
			((not (Btst fEnteredTemple))
				(fTable init:)
				(pan init:)
				(statue init:)
				(hieroglyphics init:)
				(doorway init:)
				(leftfire init:)
				(rightfire init:)
				(tent init:)
				(= [local11 0] @local0)
				(= [local16 0] @local4)
				(walkHandler addToFront: self)
				(priestess noun: N_PRIESTESS init: setLoop: 0 stopUpd:)
				(ego code: checkCode)
				(self setScript: sEnter)
			)
			((ego has: iGem)
				(acolyte1 init: stopUpd:)
				(acolyte2 init: setPri: 12 stopUpd:)
				(priestess init: x: 107 y: 176 stopUpd:)
				(chalice init: stopUpd:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								49 123
								71 118
								88 128
								59 134
							yourself:
						)
				)
				(ego useSkill: HONOR 20)
				(self setScript: sEnter)
			)
			(else
				(acolyte1 init: stopUpd:)
				(acolyte2 init: setPri: 12 stopUpd:)
				(self setScript: sEnter)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(LoadMany FALSE PRIESTESS_TALKER)
		(walkHandler delete: self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(if priestessCued
					(curRoom setScript: priestessShow)
				else
					(= toX ((user curEvent?) x?))
					(= toY ((user curEvent?) y?))
					(ego setMotion: PolyPath toX toY priestess)
				)
			)
			(else 
				(super doVerb: theVerb)
			)
		)
	)
)

(instance priestTalk of Script
	
	(method (doit)
		(super doit:)
		(if (and (not state) (< (priestess distanceTo: ego) 23))
			(priestess setMotion: 0)
			(self cue:)
		)
		(if (and (== state 5) (ego mover?))
			(ego setMotion: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoTell init: ego @local0 @local11)
				(priestessTell init: priestess @local4 @local16)
				(ego code: 0)
				(if (> (ego y?) 147)
					(ego setMotion: PolyPath 87 145 self)
				else
					(ego setMotion: 0)
					(= cycles 5)
				)
			)
			(1
				(Face ego priestess)
				(priestess
					setLoop: 5
					moveSpeed: 3
					cycleSpeed: 3
					setCycle: Walk
					setStep: 1 1
					setMotion: MoveTo 110 168 self
				)
			)
			(2
				(priestess setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_PRIESTESS V_DOIT C_PRIESTESS_TALK 0 self)
			)
			(4
				(priestess setCycle: CycleTo 0 -1 self)
			)
			(5
				(HandsOn 5 6 6)
				(= seconds 5)
			)
			(6
				(curRoom setScript: priestessShow)
			)
		)
	)
)

(instance priestessShow of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_PRIESTESS V_DOIT C_SEKHMET_CHASTISES 0 self)
			)
			(1
				(HandsOff)
				(priestess setLoop: 1 setCel: 4 setCycle: CycleTo 0 -1 self)
			)
			(2
				(messager say: N_SEKEMET V_DOIT C_SEKEMET_SPEAKS_TO_EGO 0 self)
			)
			(3
				(priestess
					cycleSpeed: 6
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(Bset fEnteredTemple)
				(curRoom setScript: stepDown 0 1)
			)
		)
	)
)

(instance stepDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(ego setMotion: PolyPath 30 78 self)
				else
					(self cue:)
				)
			)
			(1
				(acolyte2 setPri: 10)
				(stairs setPri: 10)
				(ego setMotion: MoveTo (ego x?) 76 self)
			)
			(2
				(ego
					setLoop: 3
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
			)
			(3
				(ego setLoop: -1)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance WestOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_EGO_TELL V_WALK C_WEST_OUT 0 self)
			)
			(1
				(ego
					setMotion:
						PolyPath
						(+ (ego x?) 5)
						(if (> (ego y?) 177) (- (ego y?) 5) else (ego y?))
						self
				)
			)
			(2
				(HandsOn 5 6 6)
				(self dispose:)
			)
		)
	)
)

(instance SouthOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_EGO_TELL V_WALK C_SOUTH_OUT 0 self)
			)
			(1
				(ego
					setMotion:
						PolyPath
						(if (< (ego x?) 5) (+ (ego x?) 5) else (ego x?))
						(- (ego y?) 5)
						self
				)
			)
			(2
				(HandsOn 5 6 6)
				(self dispose:)
			)
		)
	)
)

(instance sEnter of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local26 (GetTime))
				(= cycles 1)
			)
			(1 (= cycles 5))
			(2
				(= local25 (- (= local27 (GetTime)) local26))
				(ego setMotion: MoveTo 29 75 self)
			)
			(3
				(ego setLoop: -1 setMotion: MoveTo 33 78 self)
				(stairs setPri: 0)
				(acolyte2 setPri: 8)
			)
			(4
				(ego setMotion: MoveTo 36 82 self)
			)
			(5
				(ego normalize:)
				(cond 
					((not (Btst fEnteredTemple))
						(messager say: N_ENTRANCE V_DOIT C_FIRST_ENTER 0 self)
					)
					((ego has: iGem)
						(curRoom setScript: sGem)
					)
					(else
						(acolyte1 setCycle: BegLoop)
						(curRoom setScript: sNoGem)
					)
				)
			)
			(6
				(HandsOn 5 6 6)
				(self dispose:)
			)
		)
	)
)

(instance sNoGem of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ENTRANCE V_DOIT C_RETURNED 0 self)
			)
			(1
				(ego setMotion: PolyPath 68 100 self)
			)
			(2
				(acolyte2 setCycle: BegLoop self)
			)
			(3
				(messager say: N_ENTRANCE V_DOIT C_NO_GEM 0 self)
			)
			(4
				(curRoom setScript: stepDown 0 1)
			)
		)
	)
)

(instance sGem of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(acolyte1 setCycle: BegLoop self)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
			)
			(2
				(messager say: N_ENTRANCE V_DOIT C_RETURNED 0 self)
			)
			(3
				(ego setMotion: PolyPath 92 165)
			)
			(4
				(messager say: N_ENTRANCE V_DOIT C_HAVE_GEM 0 self)
			)
			(5
				(ego setMotion: PolyPath 86 171 self)
			)
			(6
				(Face ego (+ (ego x?) 10) (ego y?) self)
			)
			(7
				(priestess
					loop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: BegLoop self
				)
			)
			(8
				(messager say: N_PRIESTESS V_DOIT C_PRIESTESS_TO_SEKEMET 0 self)
			)
			(9
				(priestess
					loop: 3
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(chalice dispose:)
			)
			(10
				(priestess
					loop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(11
				(priestess
					loop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(12
				(messager say: N_PRIESTESS V_DOIT C_PRIESTESS_TO_EGO 0 self)
			)
			(13
				(ego
					view: 353
					loop: 0
					setCel: 0
					setScale: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(14
				(priestess setCycle: BegLoop self)
			)
			(15
				(globalSound stop:)
				(= seconds 3)
			)
			(16
				(ego loop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(17
				(globalSound number: 920 setLoop: 1 play: 127)
				(= cycles 3)
			)
			(18
				(ego cycleSpeed: 6)
				(Bset fSoulJudged)
				(ego drop: iGem 1)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance letSGo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 29 75 self)
			)
			(1
				(ego setLoop: -1 setMotion: MoveTo 33 78 self)
				(stairs setPri: 0)
			)
			(2
				(acolyte1 setCycle: BegLoop self)
			)
			(3
				(messager say: N_ENTRANCE V_DOIT C_LETS_GO 0 self)
			)
			(4
				(curRoom setScript: stepDown 0 1)
			)
		)
	)
)

(instance checkCode of Code

	(method (doit)
		(cond 
			((curRoom script?) 0)
			((< (ego x?) 5)
				(HandsOff)
				(curRoom setScript: WestOut)
			)
			((> (ego y?) 187)
				(HandsOff)
				(curRoom setScript: SouthOut)
			)
			((& (ego onControl:) cRED)
				(HandsOff)
				(curRoom setScript: stepDown 0 0)
			)
			(
				(and
					(& (ego onControl:) cCYAN)
					(not priestessCued)
					(not (Btst fEnteredTemple))
				)
				(HandsOff)
				(= priestessCued TRUE)
				(curRoom setScript: priestTalk)
			)
		)
	)
)

(instance acolyte1 of Actor
	(properties
		x 65
		y 80
		view 351
		signal ignrAct
	)
)

(instance acolyte2 of Actor
	(properties
		x 76
		y 128
		view 351
		loop 1
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not local24) (< (ego distanceTo: self) 35))
			(self setCycle: BegLoop sGem)
			(= local24 1)
		)
	)
)

(instance priestess of Actor
	(properties
		x 187
		y 243
		view 355
		signal ignrAct
	)
	
	(method (cue)
		(ego normalize:)
	)
)

(instance flame1 of Prop
	(properties
		x 232
		y 106
		view 350
		detailLevel 3
	)
)

(instance flame2 of Prop
	(properties
		x 162
		y 78
		view 350
		cel 4
		priority 5
		signal fixPriOn
		detailLevel 3
	)
)

(instance chalice of Prop
	(properties
		x 123
		y 148
		view 350
		loop 1
		priority 13
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance stairs of View
	(properties
		x 4
		y 115
		view 350
		loop 2
		signal (| ignrAct forceUpdOn stopUpdOn)
	)
)

(instance nPal of View
	(properties
		x 31
		y 76
		view 350
		loop 3
		cel 1
		priority 1
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance fTable of Feature
	(properties
		x 116
		y 147
		noun N_FTABLE
		nsTop 136
		nsLeft 98
		nsBottom 159
		nsRight 134
	)
)

(instance pan of Feature
	(properties
		x 207
		y 177
		noun N_PAN
		nsTop 165
		nsLeft 197
		nsBottom 189
		nsRight 217
		approachDist 30
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO)
	)
)

(instance statue of Feature
	(properties
		x 246
		y 58
		noun N_STATUE
		nsTop 6
		nsLeft 201
		nsBottom 111
		nsRight 292
	)
)

(instance hieroglyphics of Feature
	(properties
		x 114
		y 54
		noun N_HIEROGLYPHICS
		nsTop 39
		nsLeft 82
		nsBottom 70
		nsRight 146
	)
)

(instance doorway of Feature
	(properties
		x 32
		y 57
		noun N_DOORWAY
		nsTop 41
		nsBottom 74
		nsRight 64
	)
)

(instance leftfire of Feature
	(properties
		x 163
		y 86
		noun N_LEFTFIRE
		nsTop 78
		nsLeft 150
		nsBottom 95
		nsRight 177
	)
)

(instance rightfire of Feature
	(properties
		x 233
		y 116
		noun N_RIGHTFIRE
		nsTop 111
		nsLeft 216
		nsBottom 121
		nsRight 251
	)
)

(instance tent of Feature
	(properties
		x 278
		y 174
		noun N_TENT
		nsTop 161
		nsLeft 237
		nsBottom 188
		nsRight 319
	)
)

(instance egoTell of Teller
	
	(method (doChild param1)
		(cond 
			((== param1 -12)
				(messager say: N_EGO_TELL V_TELL C_HELLO 0 priestTalk)
			)
			((== param1 -13)
				(messager say: N_EGO_TELL V_TELL C_GOODBYE 0 priestTalk)
			)
		)
		(return FALSE)
	)
)

(instance priestessTell of Teller

	(method (doChild)
		(messager say: N_PRIESTESS V_TELL C_WONT_TALK 0 priestTalk)
		(priestTalk cycles: 0)
		(return FALSE)
	)
)

(instance sekhmetTalker of Talker
	(properties
		x 201
		y 30
		view 352
		loop 1
		signal ignrAct
		talkWidth 260
		color 45
		back 57
		textX -175
		textY 150
	)
	
	(method (init)
		(super init: sekhmetBust sekhmetEye sekhmetMouth &rest)
		(= theGameTime 0)
	)
	
	(method (doit)
		(if (not (mod (++ theGameTime) 40))
			(if (> (++ flameCel) 12) (= flameCel 0))
			(flame1 cel: flameCel)
			(flame2 cel: flameCel)
			(Animate (cast elements?) 0)
		)
		(super doit:)
	)
)

(instance sekhmetEye of Prop
	(properties
		nsTop -12
		nsLeft 9
		view 352
		loop 2
		cel 3
		signal ignrAct
	)
)

(instance sekhmetMouth of Prop
	(properties
		nsTop 2
		nsLeft 8
		view 352
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance sekhmetBust of Prop
	(properties
		view 352
		loop 3
	)
)
