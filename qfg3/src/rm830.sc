;;; Sierra Script 1.0 - (do not remove this comment)
(script# 830)
(include sci.sh)
(use Main)
(use TellerIcon)
(use EgoDead)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm830 0
	reeshaTalker 1
	reeshaBTalker 2
)

(local
	local0
	local1
	[local2 5] = [0 -1 -2 -3 999]
	[local7 2]
	[local9 9] = [0 -8 -7 -6 -5 -11 -12 -13 999]
	[local18 2]
)
(instance rm830 of Rm
	(properties
		noun 9
		picture 830
	)
	
	(method (init)
		(= [local7 0] @local2)
		(= [local18 0] @local9)
		(egoTell init: ego @local9 @local18)
		(ego
			setScale:
			scaleX: 128
			scaleY: 128
			init:
			noun: 2
			normalize:
		)
		(super init:)
		(theIconBar disable: 1)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						39
						189
						319
						189
						259
						138
						287
						138
						309
						138
						309
						130
						270
						130
						245
						130
						163
						90
						61
						90
						38
						189
					yourself:
				)
		)
		(sky init:)
		(if (Btst 57) (= local0 1))
		(Bset 57)
		(switch local0
			(0
				(cSound number: 830 setLoop: -1 play: 127)
				(reesha init:)
				(rubble init: stopUpd:)
				(curRoom setScript: eventOne)
			)
			(1
				(cond 
					((and (== prevRoomNum 550) (== battleResult 0)) (curRoom setScript: egoIsDead))
					((== prevRoomNum 550)
						(cSound number: 830 setLoop: -1 play: 127)
						(reesha noun: 1 init:)
						(rubble init: stopUpd:)
						(ego changeGait: 0)
						(self setScript: reeshaCollapses)
					)
					(else
						(cSound number: 832 setLoop: -1 play: 127)
						(reesha
							view: 834
							loop: 0
							setCel: 5
							x: 104
							y: 181
							noun: 1
							init:
							addToPic:
						)
						((ScriptID 35 1)
							view: 964
							setScale:
							loop: 0
							cel: 3
							x: 59
							y: 180
							init:
							addToPic:
						)
						((ScriptID 39 1)
							view: 989
							setScale:
							loop: 2
							cel: 0
							x: 106
							y: 110
							init:
							addToPic:
						)
						((ScriptID 36 1)
							view: 974
							setScale:
							loop: 2
							cel: 0
							x: 124
							y: 113
							init:
							addToPic:
						)
						((ScriptID 34 1)
							view: 969
							setScale:
							loop: 2
							cel: 0
							x: 220
							y: 127
							init:
							addToPic:
						)
						((ScriptID 40 1)
							view: 838
							x: 61
							y: 111
							setScale:
							init:
							addToPic:
						)
						(curRoom setScript: eventTwo)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(LoadMany 0 991 34 35 36 39 40 41 939)
		(super dispose:)
	)
)

(instance egoIsDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(reesha view: 837 cel: 9 init:)
				(= seconds 2)
			)
			(1
				(ego view: 6 cel: 0 loop: 0 setCycle: End self)
			)
			(2 (EgoDead))
		)
	)
)

(instance demonTaunts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(curRoom setScript: closeCombat)
			)
		)
	)
)

(instance eventTwo of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 6)
				(ego
					x: 298
					y: 133
					solvePuzzle: 338 25
					setMotion: MoveTo 168 145 self
				)
			)
			(1
				((ScriptID 41 1)
					setScale:
					init:
					x: 298
					y: 133
					setCycle: Walk
					setMotion: MoveTo 198 161 self
				)
			)
			(2
				(if (Btst 150)
					(messager say: 4 6 10 0 self)
				else
					(self cue:)
				)
			)
			(3
				(messager say: 6 6 17 0 self)
			)
			(4
				(messager say: 5 6 18 0 self)
			)
			(5
				(cSound changeTo: 833)
				(ego view: 881 setCycle: Fwd)
				(= seconds 5)
			)
			(6
				(messager say: 5 6 19 0 self)
			)
			(7
				(= temp0 (Memory memALLOC_CRIT 50))
				(= temp1 (Message msgGET 830 5 6 3 1 temp0))
				(messager sayFormat: temp1 temp0 @userName)
				(Memory memFREE temp0)
				(= seconds 3)
			)
			(8
				(curRoom newRoom: 880)
				(self dispose:)
			)
		)
	)
)

(instance blastDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rubble loop: 2 setCycle: End self)
				(globalSound number: 930 play: 127)
			)
			(1
				(rubble dispose:)
				(self dispose:)
			)
		)
	)
)

(instance useDispell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 9 setLoop: 3 setCycle: End self)
			)
			(1
				(ego
					view: 5
					drop: 16
					normalize:
					addHonor: 40
					solvePuzzle: 337 10
				)
				(reesha setScript: reeshaCollapses)
				(self dispose:)
			)
		)
	)
)

(instance closeCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 3 6 9 0 self))
			(1
				(= monsterNum 845)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance reeshaCollapses of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(reesha view: 837 loop: 0 cel: 9 setCycle: Beg self)
				(globalSound number: 932 setLoop: 1 play:)
			)
			(1
				(reesha view: 833 loop: 0 cel: 5 setCycle: Beg self)
			)
			(2
				(demonCloud
					x: 146
					y: 125
					cel: 0
					setLoop: 1
					init:
					setCycle: End self
				)
			)
			(3
				(demonCloud dispose:)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(= seconds 3)
			)
			(4
				(HandsOn)
				(curRoom setScript: portalOpens)
				(self dispose:)
			)
		)
	)
)

(instance portalOpens of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable:)
				(SetPort 0 0 190 200)
				(= seconds 2)
			)
			(1
				(globalSound number: 831 setLoop: 1 play:)
				(portal
					loop: 2
					x: 40
					y: 85
					init:
					cycleSpeed: 0
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(2
				(portal setLoop: 3 x: 46 y: 11 init:)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(SetPort 0 0 320 200)
				((ScriptID 34 1)
					setScale:
					x: 76
					y: 94
					init:
					setCycle: Walk
					setMotion: MoveTo 75 133 self
				)
				(cSound number: 834 setLoop: 1 play: self)
			)
			(3)
			(4
				(portal stopUpd:)
				((ScriptID 34 1)
					view: 969
					setScale:
					stopUpd:
					setCycle: 0
					ignoreActors: 1
				)
				((ScriptID 36 1)
					view: 975
					x: 76
					y: 94
					setScale:
					init:
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 154 113 self
				)
				(cSound number: 835 setLoop: 1 play: self)
			)
			(5
				((ScriptID 36 1) setCycle: 0 setHeading: 180)
			)
			(6
				((ScriptID 39 1)
					x: 76
					y: 94
					setScale:
					init:
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 136 110 self
				)
				(cSound number: 836 setLoop: 1 play: self)
			)
			(7
				((ScriptID 39 1) setCycle: 0 setHeading: 180 self)
			)
			(8
				((ScriptID 36 1) view: 974 ignoreActors: 1 stopUpd:)
			)
			(9
				((ScriptID 39 1) view: 989 ignoreActors: 1 stopUpd:)
				((ScriptID 40 1)
					x: 76
					y: 94
					init:
					setCycle: Walk
					setMotion: MoveTo 38 169 self
				)
				(cSound number: 837 setLoop: 1 play: self)
			)
			(10)
			(11
				((ScriptID 40 1)
					view: 838
					setCycle: 0
					ignoreActors: 1
					stopUpd:
				)
				((ScriptID 35 1)
					setScale:
					view: 967
					setLoop: 0
					x: 76
					y: 94
					init:
					setCycle: Walk
					setMotion: MoveTo 115 135 self
				)
				(cSound number: 280 setLoop: -1 play:)
			)
			(12
				(messager say: 5 6 8 0 self)
			)
			(13
				(portal dispose:)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(14
				((ScriptID 39 1) addToPic:)
				((ScriptID 36 1) addToPic:)
				((ScriptID 34 1) addToPic:)
				((ScriptID 40 1) addToPic:)
				((ScriptID 35 1)
					view: 965
					loop: 0
					cel: 0
					setCycle: End self
				)
				(globalSound number: 12 setLoop: 1 play:)
			)
			(15
				(messager say: 5 6 14 0 self)
			)
			(16
				(if (not (Btst 159))
					(messager say: 5 6 22 0 self)
				else
					(self cue:)
				)
			)
			(17
				((ScriptID 35 1) setCycle: Beg self)
			)
			(18 (reesha setCycle: End self))
			(19
				((ScriptID 35 1) setCycle: 0 addToPic:)
				(reesha ignoreActors: 1 addToPic:)
				(messager say: 5 6 15 0 self)
			)
			(20
				(cSound changeTo: 720)
				((ScriptID 41 1)
					x: 40
					y: -5
					view: 985
					setLoop: 5
					init:
					ignoreHorizon: 1
					setCycle: Rev
					setMotion: MoveTo 41 144 self
				)
			)
			(21
				((ScriptID 41 1)
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 116 148 self
				)
				(cSound changeTo: 830)
			)
			(22
				(messager say: 6 6 8 0 self)
			)
			(23
				(messager say: 5 6 16 0 self)
			)
			(24
				(self setScript: blastDoor self)
			)
			(25
				(if (== heroType 0)
					(ego get: 45)
					(messager say: 7 6 20 0 self)
				else
					(self cue:)
				)
			)
			(26
				(messager say: 5 6 1 0 self)
			)
			(27
				(ego ignoreActors: 1 setMotion: PolyPath 290 133 self)
				((ScriptID 41 1)
					ignoreActors: 1
					setMotion: MoveTo 294 133
				)
			)
			(28
				(theIconBar enable:)
				(curRoom newRoom: 840)
				(self dispose:)
			)
		)
	)
)

(instance eventOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 160
					y: 190
					setScale:
					solvePuzzle: 336 3
					setMotion: MoveTo 160 180 self
				)
			)
			(1 (reesha setCycle: End self))
			(2
				(reesha loop: 1 setCycle: End self)
			)
			(3
				(reesha loop: 2 setCycle: End self)
			)
			(4 (messager say: 1 6 8 0 self))
			(5
				(if (== heroType 3) (messager say: 4 6 10))
				(demonCloud
					x: 146
					y: 160
					init:
					moveSpeed: 0
					setCycle: End self
				)
			)
			(6
				(demonCloud loop: 1 setCycle: End self)
			)
			(7
				(demonCloud dispose:)
				(= local1 1)
				(reesha view: 837 loop: 0 cel: 0 setCycle: End self)
				(globalSound number: 932 setLoop: 1 play:)
			)
			(8 (messager say: 3 6 8 0 self))
			(9
				(HandsOn)
				(theIconBar disable: 1 4)
				(= seconds 10)
			)
			(10
				(curRoom setScript: closeCombat)
				(self dispose:)
			)
		)
	)
)

(instance rubble of Prop
	(properties
		x 209
		y 140
		view 830
	)
)

(instance reesha of Prop
	(properties
		x 145
		y 139
		noun 3
		view 833
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(26
				(curRoom setScript: useDispell)
			)
			(2
				(curRoom setScript: closeCombat)
			)
			(65 (messager say: 4 6 21))
			(4
				(curRoom setScript: closeCombat)
			)
			(81
				(curRoom setScript: closeCombat)
			)
			(83
				(curRoom setScript: closeCombat)
			)
			(88
				(curRoom setScript: closeCombat)
			)
			(33
				(curRoom setScript: closeCombat)
			)
			(20
				(curRoom setScript: closeCombat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance demonCloud of Actor
	(properties
		x 52
		y 189
		view 836
		loop 1
		signal $4000
	)
)

(instance portal of Prop
	(properties
		x 46
		y 11
		view 830
		loop 3
		signal $4000
	)
)

(instance sky of Feature
	(properties
		x 142
		y 13
		noun 8
		nsTop -2
		nsLeft 117
		nsBottom 28
		nsRight 167
		sightAngle 180
	)
)

(instance reeshaTell of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-1
				(curRoom setScript: closeCombat)
			)
			(-3
				(curRoom setScript: closeCombat)
			)
			(-2
				(curRoom setScript: closeCombat)
			)
		)
		(return 1)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(26
					(curRoom setScript: useDispell)
				)
				(2
					(curRoom setScript: closeCombat)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-5
				(not local1)
				-8
				(not local1)
				-6
				(not local1)
				-7
				(not local1)
				-11
				(== local1 1)
				-13
				(== local1 1)
				-12
				(== local1 1)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-11
					(ego setScript: closeCombat)
				)
				(-12
					(ego setScript: closeCombat)
				)
				(-13
					(curRoom setScript: demonTaunts)
				)
				(else  (return query))
			)
		)
	)
)

(instance reeshaTalker of GloryTalker
	(properties
		x 205
		y 10
		view 832
		loop 1
		talkWidth 260
		back 57
		textX -180
		textY 100
		backColor 9
	)
	
	(method (init)
		(super init: reeshaBust reeshaEyes reeshaMouth &rest)
	)
)

(instance reeshaMouth of Prop
	(properties
		nsTop 49
		nsLeft 20
		view 832
	)
)

(instance reeshaEyes of Prop
	(properties
		nsTop 40
		nsLeft 19
		view 832
		loop 2
	)
)

(instance reeshaBust of View
	(properties
		nsTop 27
		nsLeft 19
		view 832
		loop 3
	)
)

(instance reeshaBTalker of GloryTalker
	(properties
		x 10
		y 10
		view 822
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 100
		backColor 9
	)
	
	(method (init)
		(super init: reeshaBBust reeshaBEyes reeshaBMouth &rest)
	)
)

(instance reeshaBMouth of Prop
	(properties
		nsTop 31
		nsLeft 60
		view 822
	)
)

(instance reeshaBEyes of Prop
	(properties
		nsTop 24
		nsLeft 49
		view 822
		loop 2
	)
)

(instance reeshaBBust of View
	(properties
		nsTop 14
		nsLeft 53
		view 822
		loop 3
	)
)
