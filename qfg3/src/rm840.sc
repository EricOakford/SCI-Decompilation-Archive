;;; Sierra Script 1.0 - (do not remove this comment)
(script# 840)
(include game.sh) (include "840.shm")
(use Main)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm840 0
)

(local
	enterCued
)
(instance rm840 of Room
	(properties
		picture 840
	)
	
	(method (init)
		(if (Btst fEnteredMirrorRoom)
			(ego setScale: x: 160 y: 137 init: normalize:)
		else
			(ego setScale: x: 160 y: 189 init: normalize:)
		)
		(super init:)
		(cSound number: 840 setLoop: -1 play: self)
		(if (Btst fEnteredMirrorRoom)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							48 83
							129 83
							143 143
							167 164
							167 173
							132 189
							32 189
							32 83
							50 83
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							162 189
							195 158
							195 83
							319 83
							319 189
						yourself:
					)
			)
		)
		(if (Btst fEnteredMirrorRoom)
			(curRoom setScript: secondEntrance)
		else
			(Bset fEnteredMirrorRoom)
			(curRoom setScript: firstEntrance)
		)
	)
	
	(method (dispose)
		(LoadMany FALSE MONkEY_TALKER JOHARI_TALKER YESUFU_TALKER)
		(super dispose:)
	)
	
	(method (cue)
		(if enterCued
			(firstEntrance cue:)
			(= enterCued FALSE)
		)
	)
)

(instance firstEntrance of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM V_DOIT C_FIRST_ENTRANCE 0 self)
			)
			(1
				(if (Btst fSenseDanger)
					(messager say: N_ROOM V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(2
				(ego
					solvePuzzle: fEnterMirrorRoom 3
					setMotion: MoveTo 158 155 self
				)
			)
			(3
				((ScriptID JOHARI_TALKER 1)
					x: 160
					y: 200
					ignoreActors: TRUE
					setScale:
					init:
					setCycle: Walk
					setMotion: MoveTo 72 174 self
				)
				((ScriptID YESUFU_TALKER 1)
					x: 160
					y: 200
					ignoreActors: TRUE
					setScale:
					init:
					setStep: 3 2
					moveSpeed: 12
					setCycle: Walk
					setMotion: MoveTo 198 161
				)
			)
			(4
				((ScriptID MONkEY_TALKER 1)
					x: 160
					y: 200
					ignoreActors: TRUE
					setScale:
					init:
					setCycle: Walk
					setMotion: MoveTo 118 149
				)
				(reesha
					init:
					setCycle: Walk
					setMotion: MoveTo 250 168 self
				)
			)
			(5
				(= cycles 1)
			)
			(6
				(= enterCued TRUE)
			)
			(7
				(johariBiz init:)
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= cycles 2)
			)
			(8
				(= enterCued TRUE)
			)
			(9
				(manuBiz init:)
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= cycles 2)
			)
			(10
				(= enterCued TRUE)
			)
			(11
				(egoBiz init:)
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= cycles 2)
			)
			(12
				(= enterCued TRUE)
			)
			(13
				(yesufuBiz init:)
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= cycles 2)
			)
			(14
				(= enterCued TRUE)
			)
			(15
				(reeshaBiz init:)
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= seconds 3)
			)
			(16
				(= enterCued TRUE)
			)
			(17
				(johariBiz setCycle: EndLoop self)
			)
			(18
				(= enterCued TRUE)
			)
			(19
				(manuBiz setCycle: EndLoop self)
			)
			(20
				(= enterCued TRUE)
			)
			(21
				(egoBiz setCycle: EndLoop self)
			)
			(22
				(= enterCued TRUE)
			)
			(23
				(yesufuBiz setCycle: EndLoop self)
			)
			(24
				(= enterCued TRUE)
			)
			(25
				(reeshaBiz setCycle: EndLoop self)
			)
			(26
				(= seconds 2)
			)
			(27
				(curRoom newRoom: 549)
				(self dispose:)
			)
		)
	)
)

(instance secondEntrance of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(johariBiz view: 842 loop: 1 cel: 1 x: 37 y: 125 init:)
				(yesufuBiz view: 842 loop: 0 cel: 2 x: 159 y: 81 init:)
				(manuBiz view: 842 loop: 1 cel: 0 x: 83 y: 91 init:)
				(reeshaBiz view: 842 loop: 0 cel: 0 x: 149 y: 136 init:)
				(= seconds 3)
			)
			(1
				(if (not (Btst fWonGame))
					(if [egoStats MAGIC]	;EO: fixed to properly show correct messages
						(= [egoStats MANA] (ego maxMana:))
						(= [egoStats HEALTH] (ego maxHealth:))
						(messager say: N_ROOM V_DOIT C_MAGIC 0 self)
					else
						(= [egoStats HEALTH] (ego maxHealth:))
						(messager say: N_ROOM V_DOIT C_NO_MAGIC 0 self)
					)
				else
					(self cue:)
				)
			)
			(2
				(switch heroType
					(FIGHTER
						(curRoom newRoom: 851)
					)
					(PALADIN
						(curRoom newRoom: 852)
					)
					(MAGIC_USER
						(curRoom newRoom: 853)
					)
					(THIEF
						(curRoom newRoom: 854)
					)
				)
			)
		)
	)
)

(instance egoBiz of Prop
	(properties
		x 155
		y 111
		view 841
		signal ignrAct
	)
)

(instance johariBiz of Prop
	(properties
		x 29
		y 129
		view 841
		loop 3
		signal ignrAct
	)
)

(instance yesufuBiz of Prop
	(properties
		x 228
		y 116
		view 841
		loop 1
		signal ignrAct
	)
)

(instance manuBiz of Prop
	(properties
		x 84
		y 123
		view 841
		loop 4
		signal ignrAct
	)
)

(instance reesha of Actor
	(properties
		x 160
		y 200
		view 831
		cel 2
		signal ignrAct
	)
)

(instance reeshaBiz of Prop
	(properties
		x 278
		y 134
		view 841
		loop 2
		signal ignrAct
	)
)
