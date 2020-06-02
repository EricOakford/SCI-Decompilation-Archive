;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh) (include "63.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Reverse)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm63 0
)

(local
	twisterX
	twisterY
	oldX
	oldY
	local4
)
(instance twister of Actor
	(properties)
	
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
)

(instance tumbler of Actor
	(properties)
	
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
)

(instance swimmer1 of Actor
	(properties)
	
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
)

(instance swimmer2 of Actor
	(properties)
	
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
)

(instance rm63 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 58
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init: &rest)
		(StatusLine enable:)
		(NormalEgo)
		(if Night
			(if (Btst fGhostsAttack)
				(switch (Random 1 2)
					(1
						(twister view: 64 init: setScript: twistIt)
					)
					(2
						(tumbler view: 64 init: setScript: spinAcross)
					)
				)
			else
				(switch (Random 1 3)
					(1
						(twister view: 64 init: setScript: twistIt)
					)
					(2
						(tumbler view: 64 init: setScript: spinAcross)
					)
					(3
						(swimmer1 view: 64 init: hide: setScript: swimLeft)
						(swimmer2 view: 64 init: hide: setScript: swimRight)
					)
				)
			)
		else
			(self encChance: 15)
		)
		(if
			(not
				(OneOf prevRoomNum
					vBear vMinotaur vSaurus vMantray vCheetaur
					vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(twister setCycle: 0)
		(Bset fBeenIn63)
		(super dispose:)
	)
)

(instance twistIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(twister
					view: 64
					setLoop: 6
					setPri: 12
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 155 -10
					cycleSpeed: 6
					setCycle: Forward
					startUpd:
					noun: 3
					setMotion: MoveTo 200 120 self
				)
				(= twisterX (twister x?))
				(= twisterY (twister y?))
			)
			(1
				(= oldX twisterX)
				(= oldY twisterY)
				(= twisterX (Random 20 300))
				(= twisterY (Random 10 130))
				(twister
					setCycle: (if (> twisterX oldX) Forward else Reverse)
				)
				(if (Btst fGhostsAttack)
					(twister setMotion: Follow ego 30)
				else
					(twister setMotion: MoveTo twisterX twisterY self)
				)
			)
			(2
				(if
					(or
						(< (twister x?) 30)
						(> (twister x?) 290)
						(< (twister y?) 20)
					)
					(self cue:)
				else
					(self changeState: 1)
				)
			)
			(3
				(twister
					setMotion:
						MoveTo
						(cond 
							((< (twister y?) 20) (twister x?))
							((< (twister x?) 30) -30)
							((> (twister x?) 290) 350)
						)
						(if (< (twister y?) 20) -30 else (twister y?))
						self
				)
			)
			(4
				(-- global272)
				(twister stopUpd: setScript: 0)
			)
		)
	)
)

(instance spinAcross of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tumbler
					view: 64
					setLoop: 4
					setPri: 7
					setStep: 5 3
					cycleSpeed: 6
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: -15 (Random 40 110)
					setCycle: Forward
					noun: 2
					startUpd:
				)
				(if (Btst fGhostsAttack)
					(tumbler setMotion: Follow ego 30)
				else
					(tumbler setMotion: MoveTo 345 (Random 20 100) self)
				)
			)
			(1
				(-- global272)
				(tumbler stopUpd: setScript: 0)
			)
		)
	)
)

(instance swimLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer1
					view: 64
					setLoop: 7
					posn: 8 124
					show:
					setStep: 3 2
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					cycleSpeed: 6
					setCycle: Forward
					noun: 1
					startUpd:
				)
				(swimmer1 setMotion: MoveTo 8 98 self)
			)
			(1 (= ticks (Random 20 60)))
			(2
				(swimmer1 setMotion: MoveTo 8 124 self)
			)
			(3 (= ticks (Random 20 60)))
			(4
				(swimmer1 setMotion: MoveTo 8 98 self)
			)
			(5 (= ticks (Random 20 60)))
			(6
				(swimmer1 setMotion: MoveTo 8 124 self)
			)
			(7
				(-- global272)
				(swimmer1 hide: stopUpd: setScript: 0)
			)
		)
	)
)

(instance swimRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer2
					view: 64
					setLoop: 7
					posn: 240 124
					setPri: 9
					show:
					setStep: 3 2
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					cycleSpeed: 6
					setCycle: Forward
					noun: 1
					startUpd:
				)
				(swimmer2 setMotion: MoveTo 240 144 self)
			)
			(1 (= ticks (Random 20 60)))
			(2
				(swimmer2 setMotion: MoveTo 240 124 self)
			)
			(3 (= ticks (Random 20 60)))
			(4
				(swimmer2 setMotion: MoveTo 240 144 self)
			)
			(5 (= ticks (Random 20 60)))
			(6
				(swimmer2 setMotion: MoveTo 240 124 self)
			)
			(7 (= ticks (Random 20 60)))
			(8
				(swimmer2 setMotion: MoveTo 240 144 self)
			)
			(9
				(-- global272)
				(swimmer2 hide: stopUpd: setScript: 0)
			)
		)
	)
)
