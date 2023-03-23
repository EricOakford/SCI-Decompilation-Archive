;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
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
	rm72 0
)

(local
	twisterX
	twisterY
	oldTwisterX
	oldTwisterY
	local4	;unused
)
(instance twister of Actor
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(AssertPalette 64)
		(super init:)
	)
)

(instance tumbler of Actor
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(AssertPalette 64)
		(super init:)
	)
)

(instance swimmer1 of Actor
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(AssertPalette 64)
		(super init:)
	)
)

(instance swimmer2 of Actor
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(AssertPalette 64)
		(super init:)
	)
)

(instance rm72 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 62
		encChance 15
	)
	
	(method (init)
		(PalVary PALVARYTARGET 164)
		(AssertPalette 64)
		(self setRegions: FOREST)
		(super init: &rest)
		(StatusLine enable:)
		(NormalEgo)
		(if Night
			(if (Btst fGhostsAttack)
				(switch (Random 1 2)
					(1
						(twister init: modNum: 63 setScript: twistIt)
					)
					(2
						(tumbler init: modNum: 63 setScript: spinAcross)
					)
				)
			else
				(switch (Random 1 3)
					(1
						(twister view: 64 init: modNum: 63 setScript: twistIt)
					)
					(2
						(tumbler view: 64 init: modNum: 63 setScript: spinAcross)
					)
					(3
						(swimmer1
							view: 64
							init:
							hide:
							modNum: 63
							setScript: swimLeft
						)
						(swimmer2
							view: 64
							init:
							hide:
							modNum: 63
							setScript: swimRight
						)
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
		(Bset fBeenIn72)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance twistIt of Script
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
				(= oldTwisterX twisterX)
				(= oldTwisterY twisterY)
				(= twisterX (Random 20 300))
				(= twisterY (Random 10 130))
				(twister
					setCycle: (if (> twisterX oldTwisterX) Forward else Reverse)
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
				(-- ghostCount)
				(twister stopUpd: setScript: 0)
			)
		)
	)
)

(instance spinAcross of Script
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
				(-- ghostCount)
				(tumbler stopUpd: setScript: 0)
			)
		)
	)
)

(instance swimLeft of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer1
					view: 64
					setLoop: 7
					posn: 7 125
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
				(swimmer1 setMotion: MoveTo 7 98 self)
			)
			(1
				(= ticks (Random 20 80))
			)
			(2
				(swimmer1 setMotion: MoveTo 7 125 self)
			)
			(3
				(= ticks (Random 20 80))
			)
			(4
				(swimmer1 setMotion: MoveTo 7 98 self)
			)
			(5
				(= ticks (Random 20 80))
			)
			(6
				(swimmer1 setMotion: MoveTo 7 125 self)
			)
			(7
				(-- ghostCount)
				(swimmer1 hide: stopUpd: setScript: 0)
			)
		)
	)
)

(instance swimRight of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer2
					view: 64
					setLoop: 7
					posn: 260 141
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
				(swimmer2 setMotion: MoveTo 260 165 self)
			)
			(1
				(= ticks (Random 20 60))
			)
			(2
				(swimmer2 setMotion: MoveTo 260 141 self)
			)
			(3
				(= ticks (Random 20 60))
			)
			(4
				(swimmer2 setMotion: MoveTo 260 165 self)
			)
			(5
				(= ticks (Random 20 60))
			)
			(6
				(swimmer2 setMotion: MoveTo 260 141 self)
			)
			(7
				(= ticks (Random 20 60))
			)
			(8
				(swimmer2 setMotion: MoveTo 260 165 self)
			)
			(9
				(-- ghostCount)
				(swimmer2 hide: stopUpd: setScript: 0)
			)
		)
	)
)
