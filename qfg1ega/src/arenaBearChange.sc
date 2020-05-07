;;; Sierra Script 1.0 - (do not remove this comment)
(script# 171)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rmBear 0
)

(local
	i
	[local1 20]
	[local21 20]
	[local41 20] = [2 4 3 3 2 4 2 2 3 4 1 1 3 4 4 4 4 1 3 1]
	[local61 40] = [156 6 119 25 99 50 79 74 100 102 119 94 107 145 140 173 140 153 170 162 193 175 189 133 211 105 213 59 198 37 181 19 144 35 170 35 140 85 176 125]
	[local101 40]
	[local141 5] = [144 249 283 88 165]
	[local146 5] = [184 221 219 201 168]
	local151
)
(procedure (localproc_000c)
	(= i 0)
	(while (< i 20)
		(= [local1 i] (Clone star))
		;CI: The original code wouldn't properly compile, so it had to be split into segments
		([local21 i] register: [local41 i])
		([local1 i]
		;([local1 ([local21 (= [local21 i] (Clone starScriptOn))]
		;	register: [local41 i]
		;)]
			cel: 0
			init:
			setPri: 15
			posn: [local61 (* i 2)] [local61 (+ (* i 2) 1)]
			setScript: [local21 i]
		)
		(++ i)
	)
)

(procedure (localproc_0070)
	(= i 0)
	(while (< i 20)
		(= [local1 i] (Clone star))
		;CI: The original code wouldn't properly compile, so it had to be split into segments
		([local21 i] register: [local41 i])
		([local1 i]
		;([local1 ([local21 (= [local21 i] (Clone starScriptOff))]
		;	register: [local41 i]
		;)]
			cel: (- [local41 i] 1)
			init:
			setPri: 15
			posn: [local61 (* i 2)] [local61 (+ (* i 2) 1)]
			setScript: [local21 i]
		)
		(++ i)
	)
)

(procedure (localproc_00db)
	(= i 19)
	(while (>= i 0)
		([local21 i] dispose:)
		([local1 i] dispose:)
		(-- i)
	)
)

(procedure (BarnardDies)
	(curRoom drawPic: 420)
	(bearRightPaw init: setPri: 6 stopUpd:)
	(bearLeftPaw init: setPri: 5 stopUpd:)
	(bearHead init: setPri: 4 setLoop: 0 cel: 0 stopUpd:)
)

(procedure (BarnardFree)
	(curRoom drawPic: 420)
	(bearRightPaw init: setPri: 6 stopUpd:)
	(bearLeftPaw init: setPri: 5 stopUpd:)
	(bearHead init: setPri: 4 setLoop: 3 cel: 0 stopUpd:)
)

(procedure (localproc_0190)
	(localproc_00db)
	(bearHead dispose:)
	(bearRightPaw dispose:)
	(bearLeftPaw dispose:)
	(curRoom drawPic: 400 8)
)

(procedure (localproc_01bb)
	(baronet init: setPri: 4 stopUpd:)
	(baronHead init: setPri: 5 setLoop: 0 cel: 0 stopUpd:)
	(baronRightArm init: setPri: 7 setLoop: 6 cel: 0 stopUpd:)
	(baronLeftArm init: setPri: 6 setLoop: 5 cel: 0 stopUpd:)
)

(procedure (localproc_021d)
	(baronet init: setPri: 4 stopUpd:)
	(baronHead init: setPri: 5 setLoop: 1 cel: 0 stopUpd:)
	(baronRightArm init: setPri: 7 setLoop: 6 cel: 0 stopUpd:)
	(baronLeftArm init: setPri: 6 setLoop: 5 cel: 0 stopUpd:)
)

(procedure (localproc_027f)
	(baronet dispose:)
	(baronHead dispose:)
	(baronRightArm dispose:)
	(baronLeftArm dispose:)
	(curRoom drawPic: 14 3)
	(NormalEgo)
	(ego init: posn: 141 140)
	(drip init: setScript: dripScript)
	(= local151 (Random 0 4))
	(Bset BEAR_GONE)
	(if (Btst DEFEATED_BEAR)
		(baronetSmall init: x: 239 y: 135 loop: 0)
	else
		(baronetSmall init: x: 239 y: 135 loop: 1)
	)
)

(instance bearChange of Sound
	(properties
		number 70
		priority 3
	)
)

(instance rmBear of Room
	(properties
		picture 420
	)
	
	(method (init)
		(Load PICTURE 400)
		(Load PICTURE 14)
		(LoadMany VIEW vBearHumanFight vBearFight vBearDefeated vArenaDazzle)
		(StatusLine enable:)
		(super init:)
		(HandsOff)
		(= monsterNum 0)
		(if (Btst DEFEATED_BEAR)
			(BarnardDies)
			(self setScript: bearDying)
		else
			(BarnardFree)
			(self setScript: bearFreed)
		)
	)
	
	(method (doit)
		(cond 
			((== (ego edgeHit?) 2) (curRoom newRoom: 15))
			((== (ego edgeHit?) 3) (curRoom newRoom: 13))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= monsterHealth (= monsterNum FALSE))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/stalactite')
								(HighPrint 171 0)
								;They're like stalagmites, but they go the other way.
								)
							((Said '/stalagmite')
								(HighPrint 171 1)
								;They're like stalactites, but they go the other way.
								)
							((Said '[<at,around][/!*,cave,room]')
								(HighPrint 171 2)
								;The cavern contains some impressive formations and is rather beautiful, as caves go.
								)
							((Said '/wall,fungus,north,west,formation')
								(HighPrint 171 3)
								;The stalactites, stalagmites, and cave walls glow from a phosphorescent fungus growing there.
								)
							((or (Said '<up') (Said '/ceiling'))
								(HighPrint 171 4)
								;The stalactites grow slowly.
								(HighPrint 171 5)
								;...or are they stalagmites?
								)
							((or (Said '<down') (Said '/floor'))
								(HighPrint 171 6)
								;The stalagmites grow slowly.
								(HighPrint 171 7)
								;...or are they stalactites?
								)
							((Said '/south,entrance,open')
								(HighPrint 171 8)
								;The light from outside illuminates the cave opening.
								)
							((Said '/east')
								(HighPrint 171 9)
								;Beyond the bear, the cave seems to continue.
								)
							((Said '/bear,animal,creature,monster')
								(if (Btst DEFEATED_BEAR)
									(HighPrint 171 10)
									;The bear is gone.  All that is left is the body of a dead man.
								else
									(HighPrint 171 11)
									;The bear is gone.
								)
							)
							((Said '/man,barnard')
								(if (Btst DEFEATED_BEAR)
									(HighPrint (Format @local101 171 12 userName)
										;He's dead, %s.
										)
								else
									(HighPrint 171 13)
									;The man is gone.
									
								)
							)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/fungus')
								(HighPrint 171 14)
								;The fungus is slimy and stuck tight to the cave walls.
								)
							((Said '/bear,stalactite,stalagmite')
								(HighPrint 171 15)
								;You're kidding, right?
								)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 171 16)
									;There is no magic in the cavern.
									)
							)
							(else  (event claimed: FALSE))
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance bearDying of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bearHead cel: 1)
				(bearChange init: play:)
				(= cycles 3)
			)
			(1
				(SetCursor theCursor 0)
				(bearHead cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 5)
			)
			(2
				(localproc_000c)
				(= cycles 16)
			)
			(3
				(localproc_0190)
				(localproc_0070)
				(self cue:)
			)
			(4
				(localproc_01bb)
				(= cycles 4)
			)
			(5
				(baronLeftArm loop: 7 cel: 1 stopUpd:)
				(baronRightArm loop: 7 cel: 0 stopUpd:)
				(baronHead cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 13)
			)
			(6
				(localproc_00db)
				(localproc_027f)
				(= cycles 11)
			)
			(7
				(baronetSmall cycleSpeed: 3 setCycle: EndLoop self)
			)
			(8
				(if (Btst READ_BARNARD_BULLETIN)
					(HighPrint 171 17)
					;The body lying there looks a lot like the picture of the Baronet von Spielburg on the poster at the guild hall.
					;You have a funny feeling that you may have made a slight tactical error.
				else
					(HighPrint 171 18)
					;You have a funny feeling that you may have made a slight tactical error.
				)
				(baronetSmall
					cel: 0
					loop: 4
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(9
				(Bclr DEFEATED_BEAR)
				(baronetSmall dispose:)
				(SetCursor theCursor TRUE)
				(HandsOn)
			)
		)
	)
)

(instance bearFreed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bearChange init: play:)
				(= cycles 3)
			)
			(1
				(SetCursor theCursor FALSE)
				(localproc_000c)
				(= cycles 20)
			)
			(2
				(localproc_0190)
				(localproc_0070)
				(self cue:)
			)
			(3
				(localproc_021d)
				(= cycles 4)
			)
			(4
				(baronHead loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(baronHead stopUpd:)
				(= cycles 4)
			)
			(6
				(baronLeftArm loop: 5 cel: 0 setCycle: Forward)
				(= cycles 5)
			)
			(7
				(baronLeftArm stopUpd:)
				(baronHead setCycle: BegLoop self startUpd:)
			)
			(8
				(baronHead loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(9
				(baronHead stopUpd:)
				(= cycles 4)
			)
			(10
				(baronRightArm loop: 6 cel: 0 setCycle: Forward)
				(= cycles 5)
			)
			(11
				(baronRightArm stopUpd:)
				(= cycles 4)
			)
			(12
				(baronHead setCycle: BegLoop)
				(localproc_00db)
				(= cycles 5)
			)
			(13
				(baronHead loop: 3 cel: 0 setCycle: EndLoop self)
				(= cycles 5)
			)
			(14
				(localproc_027f)
				(= cycles 11)
			)
			(15
				(baronetSmall setCycle: EndLoop self)
			)
			(16
				(HighPrint 171 19)
				;"You have the honor of meeting the Baronet Barnard von Spielburg.  We are pleased that you broke our enchantment.
				;Perhaps our father the Baron will reward you should you ever visit our castle."
				(= cycles 3)
			)
			(17
				(baronetSmall
					loop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(18
				(baronetSmall dispose:)
				(HighPrint 171 20)
				;The arrogance of the Baronet astonishes you.  Perchance the kobold Magic User had a reason for changing him into a bear.
				(SetCursor theCursor 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance starScriptOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (* register 3)))
			(1 (client setCycle: EndLoop self))
			(2
				(client loop: 1 cel: 0 setCycle: Forward)
			)
		)
	)
)

(instance starScriptOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 1 cel: 0 setCycle: Forward)
				(= cycles (* register 3))
			)
			(1
				(client loop: 0 cel: 5 setCycle: BegLoop)
			)
		)
	)
)

(instance dripScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(drip
					posn: [local141 local151] [local146 local151]
					setCycle: EndLoop
				)
				(= cycles (Random 20 40))
			)
			(1
				(= local151 (Random 0 4))
				(self changeState: 0)
			)
		)
	)
)

(instance bearHead of Prop
	(properties
		y 68
		x 156
		view vBearFight
		priority 4
	)
)

(instance bearRightPaw of View
	(properties
		y 42
		x 115
		view vBearFight
		loop 1
		priority 6
	)
)

(instance bearLeftPaw of View
	(properties
		y 39
		x 199
		view vBearFight
		loop 2
		priority 5
	)
)

(instance baronet of View
	(properties
		y 199
		x 158
		view vBearHumanFight
		loop 4
		priority 4
	)
)

(instance baronHead of Prop
	(properties
		y 72
		x 156
		view vBearHumanFight
		priority 5
	)
)

(instance baronHeadSmall of Prop
	(properties
		y 140
		x 130
		z 25
		view vBearDefeated
		loop 2
		priority 5
	)
)

(instance baronetSmall of Actor
	(properties
		y 140
		x 130
		view vBearDefeated
		priority 12
	)
)

(instance baronRightArm of Prop
	(properties
		y 60
		x 135
		view vBearHumanFight
		loop 6
		priority 7
	)
)

(instance baronLeftArm of Prop
	(properties
		y 61
		x 177
		view vBearHumanFight
		loop 5
		priority 6
	)
)

(instance star of Prop
	(properties
		y 95
		x 107
		view vArenaDazzle
		cel 1
		priority 12
	)
)

(instance drip of Prop
	(properties
		y 204
		x 79
		view vKoboldCave
	)
)
