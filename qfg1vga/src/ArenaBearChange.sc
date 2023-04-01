;;; Sierra Script 1.0 - (do not remove this comment)
(script# 171)
(include game.sh) (include "171.shm")
(use Main)
(use Procs)
(use Talker)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rmBear 0
	bernieTalker 1
)

(local
	local0	;unused
	dripX = [28 63 83 102 235 295 195 39 251]
	dripY = [93 85 79 79 86 99 98 103 90]
)
(instance rmBear of Room
	(properties
		noun N_ROOM
		picture pBearToBarnard
	)
	
	(method (init)
		(theGame setCursor: INVIS_CURSOR)
		(theIconBar disable:)
		(Load PICTURE pBlack)
		(LoadMany VIEW vBearFight vBearDefeated)
		(StatusLine enable:)
		(super init:)
		(HandsOff)
		(= monsterNum FALSE)
		(baronet init: setPri: 4 setLoop: 0 cel: 0 stopUpd:)
		(stunnedWarrior init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 88
						293 94
						244 94
						94 121
						41 107
						43 119
						81 124
						55 133
						19 130
						0 135
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						110 189
						184 157
						177 178
						221 181
						250 166
						231 162
						209 174
						198 161
						319 109
						319 189
					yourself:
				)
		)
		(self setScript: turnBearBack)
	)
	
	(method (doit)
		(cond 
			((== (ego edgeHit?) EAST)
				(curRoom newRoom: 15)
			)
			((== (ego edgeHit?) SOUTH)
				(curRoom newRoom: 13)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= monsterHealth (= monsterNum 0))
		(super dispose:)
	)
)

(instance baronet of Actor
	(properties
		x 203
		y 126
		view 423
		priority 4
	)
	
	(method (init)
		(= nightPalette 1422)
		(PalVary PALVARYTARGET 1422)
		(AssertPalette 422)
		(super init:)
	)
)

(instance drip of Prop
	(properties
		view 14
	)
)

(instance stunnedWarrior of Prop
	(properties
		x 97
		y 136
		view vEgoUnarmed
		cycleSpeed 12
	)
)

(instance bearChange of Sound
	(properties
		number 70
		priority 3
	)
)

(instance turnBearBack of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bearChange init: play:)
				(= ticks 30)
			)
			(1
				(stunnedWarrior setCycle: EndLoop)
				(baronet cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2
				(= ticks 120)
			)
			(3
				(if (Btst fBearDying)
					(curRoom setScript: bearDying)
				else
					(curRoom setScript: bearFreed)
				)
			)
		)
	)
)

(instance bearDying of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stunnedWarrior dispose:)
				(baronet hide:)
				(self cue:)
			)
			(1
				(curRoom drawPic: rBearCave WIPERIGHT)
				(NormalEgo)
				(ego init: posn: 107 150)
				(baronet
					view: vBearDefeated
					x: 173
					y: 135
					setPri: 5
					setLoop: 0
					cel: 0
					cycleSpeed: 12
					show:
					setCycle: EndLoop self
				)
				(drip init: setScript: dripScript)
				(Bset fBearGone)
				(= cycles 11)
			)
			(2
				(if (Btst fReadBarnardBulletin)
					(messager say: N_ROOM NULL C_RECOGNIZECORPSE 1 self)
				else
					(messager say: N_ROOM NULL C_OOPS 1 self)
				)
			)
			(3
				(baronet
					cel: 0
					loop: 4
					cycleSpeed: 12
					setCycle: CycleTo 6 1 self
				)
			)
			(4
				(baronet dispose:)
				(Bclr fBearDying)
				(theIconBar enable:)
				(theGame setCursor: normalCursor 220 180)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bearFreed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stunnedWarrior dispose:)
				(baronet hide:)
				(Bset fBearGone)
				(self cue:)
			)
			(1
				(curRoom drawPic: rBearCave WIPERIGHT)
				(NormalEgo)
				(ego init: posn: 107 150)
				(baronet
					view: vBearDefeated
					setPri: 5
					setLoop: 1
					cel: 0
					x: 182
					y: 135
					show:
					stopUpd:
				)
				(drip init: setScript: dripScript)
				(= ticks 60)
			)
			(2
				(messager say: N_ROOM NULL C_MEETBARNARD 1 self)
			)
			(3
				(baronet loop: 3 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(4
				(messager say: N_ROOM NULL C_AFTERMATH 1 self)
			)
			(5
				(baronet dispose:)
				(theGame setCursor: normalCursor 220 150)
				(theIconBar enable:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance dripScript of Script	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(= ticks (Random 1 200))
			)
			(1
				(= i (Random 0 8))
				(client
					cel: 0
					posn: [dripX i] [dripY i]
					setCycle: EndLoop self
				)
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance bernieTalker of Talker
	(properties
		x 10
		y 10
		view vBarnard14Talker
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= font userFont)
		(super init: bernieBust bernieEye bernieMouth &rest)
	)
)

(instance bernieBust of Prop
	(properties
		view vBarnard14Talker
	)
)

(instance bernieEye of Prop
	(properties
		nsTop 33
		nsLeft 47
		view vBarnard14Talker
		loop 2
	)
)

(instance bernieMouth of Prop
	(properties
		nsTop 53
		nsLeft 46
		view vBarnard14Talker
		loop 1
	)
)
