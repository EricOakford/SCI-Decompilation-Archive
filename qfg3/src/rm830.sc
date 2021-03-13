;;; Sierra Script 1.0 - (do not remove this comment)
(script# 830)
(include game.sh) (include "830.shm")
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
	roomState
	reeshaDemon
	local2 = [0 -1 -2 -3 999]
	[local7 2]
	local9 = [0 -8 -7 -6 -5 -11 -12 -13 999]
	[local18 2]
)
(instance rm830 of Room
	(properties
		noun N_ROOM
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
			noun: N_EGO_TELL
			normalize:
		)
		(super init:)
		(theIconBar disable: ICON_WALK)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						39 189
						319 189
						259 138
						287 138
						309 138
						309 130
						270 130
						245 130
						163 90
						61 90
						38 189
					yourself:
				)
		)
		(sky init:)
		(if (Btst fFoundReeshaka)
			(= roomState 1)
		)
		(Bset fFoundReeshaka)
		(switch roomState
			(0
				(cSound number: 830 setLoop: -1 play: 127)
				(reesha init:)
				(rubble init: stopUpd:)
				(curRoom setScript: eventOne)
			)
			(1
				(cond 
					((and (== prevRoomNum 550) (== battleResult battleEGOLOST))
						(curRoom setScript: egoIsDead)
					)
					((== prevRoomNum 550)
						(cSound number: 830 setLoop: -1 play: 127)
						(reesha noun: N_REESHAKA init:)
						(rubble init: stopUpd:)
						(ego changeGait: MOVE_WALK)
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
							noun: N_REESHAKA
							init:
							addToPic:
						)
						((ScriptID RAKEESH_TALKER 1)
							view: 964
							setScale:
							loop: 0
							cel: 3
							x: 59
							y: 180
							init:
							addToPic:
						)
						((ScriptID YESUFU_TALKER 1)
							view: 989
							setScale:
							loop: 2
							cel: 0
							x: 106
							y: 110
							init:
							addToPic:
						)
						((ScriptID JOHARI_TALKER 1)
							view: 974
							setScale:
							loop: 2
							cel: 0
							x: 124
							y: 113
							init:
							addToPic:
						)
						((ScriptID UHURA_TALKER 1)
							view: 969
							setScale:
							loop: 2
							cel: 0
							x: 220
							y: 127
							init:
							addToPic:
						)
						((ScriptID HARAMI_TALKER 1)
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
		(LoadMany FALSE
			JUMP UHURA_TALKER RAKEESH_TALKER
			JOHARI_TALKER YESUFU_TALKER HARAMI_TALKER
			MONkEY_TALKER OSC
		)
		(super dispose:)
	)
)

(instance egoIsDead of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(reesha view: 837 cel: 9 init:)
				(= seconds 2)
			)
			(1
				(ego view: 6 cel: 0 loop: 0 setCycle: EndLoop self)
			)
			(2
				(EgoDead)
			)
		)
	)
)

(instance demonTaunts of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(curRoom setScript: closeCombat)
			)
		)
	)
)

(instance eventTwo of Script
	
	(method (changeState newState &tmp ptr egoName)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fInMainGame)
				(ego
					x: 298
					y: 133
					solvePuzzle: fWinGame 25
					setMotion: MoveTo 168 145 self
				)
			)
			(1
				((ScriptID MONkEY_TALKER 1)
					setScale:
					init:
					x: 298
					y: 133
					setCycle: Walk
					setMotion: MoveTo 198 161 self
				)
			)
			(2
				(if (Btst fSenseDanger)
					(messager say: N_ENDING V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(3
				(messager say: N_MANU V_DOIT C_END1 0 self)
			)
			(4
				(messager say: N_EVENT V_DOIT C_END2 0 self)
			)
			(5
				(cSound changeTo: 833)
				(ego view: 881 setCycle: Forward)
				(= seconds 5)
			)
			(6
				(messager say: N_EVENT V_DOIT C_END3 0 self)
			)
			(7
				(= ptr (Memory MNeedPtr 50))
				(= egoName (Message MsgGet 830 N_EVENT V_DOIT C_NAME 1 ptr))
				(messager sayFormat: egoName ptr @userName)
				(Memory MDisposePtr ptr)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rubble loop: 2 setCycle: EndLoop self)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 9 setLoop: 3 setCycle: EndLoop self)
			)
			(1
				(ego
					view: 5
					drop: 16
					normalize:
					addHonor: 40
					solvePuzzle: fDispelReeshaka 10
				)
				(reesha setScript: reeshaCollapses)
				(self dispose:)
			)
		)
	)
)

(instance closeCombat of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_DEMON V_DOIT C_START_BATTLE 0 self)
			)
			(1
				(= monsterNum vDemon)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance reeshaCollapses of Script

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(reesha view: 837 loop: 0 cel: 9 setCycle: BegLoop self)
				(globalSound number: 932 setLoop: 1 play:)
			)
			(1
				(reesha view: 833 loop: 0 cel: 5 setCycle: BegLoop self)
			)
			(2
				(demonCloud
					x: 146
					y: 125
					cel: 0
					setLoop: 1
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(demonCloud dispose:)
				(DrawPic (curRoom picture?) PIXELDISSOLVE)
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
					setCycle: Forward
				)
				(= seconds 4)
			)
			(2
				(portal setLoop: 3 x: 46 y: 11 init:)
				(DrawPic (curRoom picture?) PIXELDISSOLVE)
				(SetPort 0 0 320 200)
				((ScriptID UHURA_TALKER 1)
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
				((ScriptID UHURA_TALKER 1)
					view: 969
					setScale:
					stopUpd:
					setCycle: 0
					ignoreActors: TRUE
				)
				((ScriptID JOHARI_TALKER 1)
					view: 975
					x: 76
					y: 94
					setScale:
					init:
					setCycle: Walk
					ignoreActors: TRUE
					setMotion: MoveTo 154 113 self
				)
				(cSound number: 835 setLoop: 1 play: self)
			)
			(5
				((ScriptID JOHARI_TALKER 1) setCycle: 0 setHeading: 180)
			)
			(6
				((ScriptID YESUFU_TALKER 1)
					x: 76
					y: 94
					setScale:
					init:
					setCycle: Walk
					ignoreActors: TRUE
					setMotion: MoveTo 136 110 self
				)
				(cSound number: 836 setLoop: 1 play: self)
			)
			(7
				((ScriptID YESUFU_TALKER 1) setCycle: 0 setHeading: 180 self)
			)
			(8
				((ScriptID JOHARI_TALKER 1) view: 974 ignoreActors: TRUE stopUpd:)
			)
			(9
				((ScriptID YESUFU_TALKER 1) view: 989 ignoreActors: TRUE stopUpd:)
				((ScriptID HARAMI_TALKER 1)
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
				((ScriptID HARAMI_TALKER 1)
					view: 838
					setCycle: 0
					ignoreActors: TRUE
					stopUpd:
				)
				((ScriptID RAKEESH_TALKER 1)
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
				(messager say: N_EVENT V_DOIT C_REESHAKA 0 self)
			)
			(13
				(portal dispose:)
				(DrawPic (curRoom picture?) PIXELDISSOLVE)
				(= seconds 2)
			)
			(14
				((ScriptID YESUFU_TALKER 1) addToPic:)
				((ScriptID JOHARI_TALKER 1) addToPic:)
				((ScriptID UHURA_TALKER 1) addToPic:)
				((ScriptID HARAMI_TALKER 1) addToPic:)
				((ScriptID RAKEESH_TALKER 1)
					view: 965
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(globalSound number: 12 setLoop: 1 play:)
			)
			(15
				(messager say: N_EVENT V_DOIT C_HEAL_REESHAKA 0 self)
			)
			(16
				(if (not (Btst fMetHarami))
					(messager say: N_EVENT V_DOIT C_MEET_HARAMI 0 self)
				else
					(self cue:)
				)
			)
			(17
				((ScriptID RAKEESH_TALKER 1) setCycle: BegLoop self)
			)
			(18 (reesha setCycle: EndLoop self))
			(19
				((ScriptID RAKEESH_TALKER 1) setCycle: 0 addToPic:)
				(reesha
					ignoreActors: TRUE
					addToPic:
				)
				(messager say: N_EVENT V_DOIT C_PORTAL_OPENS 0 self)
			)
			(20
				(cSound changeTo: 720)
				((ScriptID MONkEY_TALKER 1)
					x: 40
					y: -5
					view: 985
					setLoop: 5
					init:
					ignoreHorizon: TRUE
					setCycle: Reverse
					setMotion: MoveTo 41 144 self
				)
			)
			(21
				((ScriptID MONkEY_TALKER 1)
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 116 148 self
				)
				(cSound changeTo: 830)
			)
			(22
				(messager say: N_MANU V_DOIT C_REESHAKA 0 self)
			)
			(23
				(messager say: N_EVENT V_DOIT C_ROCKS_CLEARED 0 self)
			)
			(24
				(self setScript: blastDoor self)
			)
			(25
				(if (== heroType FIGHTER)
					(ego get: iMagicSpear)
					(messager say: N_GET_SPEAR V_DOIT C_SPEAR 0 self)
				else
					(self cue:)
				)
			)
			(26
				(messager say: N_EVENT V_DOIT C_DEMONS 0 self)
			)
			(27
				(ego
					ignoreActors: TRUE
					setMotion: PolyPath 290 133 self
				)
				((ScriptID MONkEY_TALKER 1)
					ignoreActors: TRUE
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 160
					y: 190
					setScale:
					solvePuzzle: fFindReeshaka 3
					setMotion: MoveTo 160 180 self
				)
			)
			(1
				(reesha setCycle: EndLoop self)
			)
			(2
				(reesha loop: 1 setCycle: EndLoop self)
			)
			(3
				(reesha loop: 2 setCycle: EndLoop self)
			)
			(4
				(messager say: N_REESHAKA V_DOIT C_REESHAKA 0 self)
			)
			(5
				(if (== heroType PALADIN)
					(messager say: N_ENDING V_DOIT C_SENSE_DANGER)
				)
				(demonCloud
					x: 146
					y: 160
					init:
					moveSpeed: 0
					setCycle: EndLoop self
				)
			)
			(6
				(demonCloud loop: 1 setCycle: EndLoop self)
			)
			(7
				(demonCloud dispose:)
				(= reeshaDemon TRUE)
				(reesha view: 837 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 932 setLoop: 1 play:)
			)
			(8
				(messager say: N_DEMON V_DOIT C_REESHAKA 0 self)
			)
			(9
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_TALK)
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
		noun N_DEMON
		view 833
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DISPEL
				(curRoom setScript: useDispell)
			)
			(V_TALK
				(curRoom setScript: closeCombat)
			)
			(V_REST
				(messager say: N_ENDING V_DOIT C_CANT_SLEEP)
			)
			(V_DO
				(curRoom setScript: closeCombat)
			)
			(V_FLAME
				(curRoom setScript: closeCombat)
			)
			(V_FORCEBOLT
				(curRoom setScript: closeCombat)
			)
			(V_LIGHTNING
				(curRoom setScript: closeCombat)
			)
			(V_ROCK
				(curRoom setScript: closeCombat)
			)
			(V_DAGGER
				(curRoom setScript: closeCombat)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance demonCloud of Actor
	(properties
		x 52
		y 189
		view 836
		loop 1
		signal ignrAct
	)
)

(instance portal of Prop
	(properties
		x 46
		y 11
		view 830
		loop 3
		signal ignrAct
	)
)

(instance sky of Feature
	(properties
		x 142
		y 13
		noun N_SKY
		nsTop -2
		nsLeft 117
		nsBottom 28
		nsRight 167
		sightAngle 180
	)
)

(instance reeshaTell of Teller
	
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
		(return TRUE)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DISPEL
					(curRoom setScript: useDispell)
				)
				(V_TALK
					(curRoom setScript: closeCombat)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance egoTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-5
				(not reeshaDemon)
				-8
				(not reeshaDemon)
				-6
				(not reeshaDemon)
				-7
				(not reeshaDemon)
				-11
				(== reeshaDemon 1)
				-13
				(== reeshaDemon 1)
				-12
				(== reeshaDemon 1)
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
				(else
					(return query)
				)
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
