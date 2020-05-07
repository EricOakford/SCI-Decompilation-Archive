;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh)
(use Main)
(use LoadMany)
(use Reverse)
(use Follow)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm64 0
)

(local
	local0
	local1
	ghostsDrainEgo
	fallingInHole
)
(procedure (LifeDrained)
	(EgoDead 64 0
		#title { Oh-h-h-h-h-h-h!_} 
		#width 250
		#icon vEgoDefeatedMagic 0 0)
		;As the ghosts suck the life out of your body, and you crumple into the dust of the old graveyard,
		;you think unspeakable thoughts of torture and torment directed at programmers, game designers, and vicious artists named Kenn.
)

(instance riser of Actor
	(properties)
)

(instance longOne of Actor
	(properties)
)

(instance mandrake of View
	(properties
		y 122
		x 130
		view vMandrake
		loop 3
	)
)

(instance etching of PicView
	(properties
		y 133
		x 227
		view vMandrake
	)
)

(instance gravestone of PicView
	(properties
		y 119
		x 124
		view vMandrake
		loop 2
		priority 6
	)
)

(instance rm64 of Room
	(properties
		picture 64
		style DISSOLVE
		horizon 116
		north 52
		west 63
	)
	
	(method (init)
		(LoadMany SCRIPT GHOSTS CEMETERY)
		(LoadMany VIEW vGhosts vMandrake vEgoDefeatedMagic)
		(super init:)
		(StatusLine enable:)
		(cSound fade:)
		(self setRegions: CEMETERY)
		(addToPics
			add: etching gravestone
			eachElementDo: #init
			doit:
		)
		(if
			(and
				(not (ego has: iMandrake))
				(< (+ mandrakeDay 1) Day)
			)
			(mandrake setPri: 8 stopUpd: init:)
		)
		(if Night
			(= deathMusic 37)
			(riser
				view: vGhosts
				setLoop: 3
				ignoreActors:
				ignoreHorizon:
				illegalBits: 0
				posn: 0 1000
				init:
				cycleSpeed: 1
				stopUpd:
			)
			(longOne
				view: vGhosts
				setLoop: 7
				setPri: 6
				ignoreActors:
				ignoreHorizon:
				illegalBits: 0
				posn: 21 126
				init:
				setStep: 3 1
				cycleSpeed: 1
				setCycle: Forward
				stopUpd:
			)
			((ScriptID GHOSTS 5) init:)
			((ScriptID GHOSTS 6) init:)
			((ScriptID GHOSTS 7) init:)
			(ghostMusic init: play:)
		)
		(= ghostCount 0)
		(= local1 (Random 0 7))
		(NormalEgo)
		(ego init:)
		(if (and Night (Btst fGhostOil)) (SolvePuzzle POINTS_USEUNDEADUNGUENT 2))
		(switch prevRoomNum
			(72
				(ego posn: 160 188 setMotion: MoveTo 160 180)
				(self setScript: safeIntro)
			)
			(52
				(ego posn: 160 117 setMotion: MoveTo 160 125)
				(self setScript: safeIntro)
			)
			(else 
				(ego posn: 1 172 setMotion: MoveTo 25 172)
				(self setScript: safeIntro)
			)
		)
	)
	
	(method (doit)
		(if (and (== (ego edgeHit?) 3) (not fallingInHole))
			(curRoom newRoom: 72)
		)
		(if
		(and (not fallingInHole) (== (ego onControl: origin) cYELLOW))
			(= fallingInHole TRUE)
			(self setScript: fallInHole)
		)
		(cond 
			(
				(and
					(< 68 (ego x?))
					(< (ego x?) 270)
					(< 120 (ego y?))
					(< (ego y?) 160)
					(not ghostsDrainEgo)
					(not (Btst fGhostOil))
					(not (Btst fGhostsAttack))
					Night
				)
				(Bset fGhostsAttack)
			)
			(
				(and
					(Btst fGhostsAttack)
					(not ghostsDrainEgo)
					(or
						(< (ego distanceTo: riser) 35)
						(< (ego distanceTo: (ScriptID GHOSTS 5)) 35)
						(< (ego distanceTo: (ScriptID GHOSTS 7)) 35)
						(< (ego distanceTo: (ScriptID GHOSTS 6)) 35)
						(< (ego distanceTo: longOne) 35)
					)
				)
				(= ghostsDrainEgo TRUE)
				(Bclr fGhostsAttack)
				(self setScript: gotHim)
			)
		)
		(if (and Night (< ghostCount 3))
			(switch local1
				(0
					(if (== ((ScriptID GHOSTS 7) script?) 0)
						(++ ghostCount)
						(++ local1)
						((ScriptID GHOSTS 7) setScript: (ScriptID GHOSTS 3))
					)
				)
				(1
					(if (== (riser script?) 0)
						(++ ghostCount)
						(++ local1)
						(riser setScript: riseUpRight)
					)
				)
				(2
					(if (== ((ScriptID GHOSTS 6) script?) 0)
						(++ ghostCount)
						(++ local1)
						((ScriptID GHOSTS 6) setScript: (ScriptID GHOSTS 2))
					)
				)
				(3
					(if (== ((ScriptID GHOSTS 7) script?) 0)
						(++ ghostCount)
						(++ local1)
						((ScriptID GHOSTS 7) setScript: (ScriptID GHOSTS 4))
					)
				)
				(4
					(if (== ((ScriptID GHOSTS 5) script?) 0)
						(++ ghostCount)
						(++ local1)
						((ScriptID GHOSTS 5) setScript: (ScriptID GHOSTS 0))
					)
				)
				(5
					(if (== (longOne script?) 0)
						(++ ghostCount)
						(++ local1)
						(longOne setScript: peekABoo)
					)
				)
				(6
					(if (== (riser script?) 0)
						(++ ghostCount)
						(++ local1)
						(riser setScript: riseUpLeft)
					)
				)
				(7
					(if (== ((ScriptID GHOSTS 6) script?) 0)
						(++ ghostCount)
						(++ local1)
						((ScriptID GHOSTS 6) setScript: (ScriptID GHOSTS 1))
					)
				)
				(8
					(if (== (riser script?) 0)
						(= local1 0)
						(++ ghostCount)
						(riser setScript: riseDownLeft)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript riseUpLeft)
		(DisposeScript riseUpRight)
		(DisposeScript riseDownLeft)
		(DisposeScript peekABoo)
		(if Night
			(= deathMusic (SoundFX 26))
			(Bset VISITED_GRAVEYARD_NIGHTTIME)
		else
			(Bset VISITED_GRAVEYARD_DAYTIME)
		)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'climb,climb[/wall]')
						(if Night
							(if (> (ego x?) 256)
								(if (TrySkill CLIMB tryClimbIntoTown 0)
									(curRoom newRoom: 330)
								else
									(HighPrint 64 1)
									;The wall is too difficult for your level of skill, but keep practicing.
								)
							else
								(HighPrint 64 2)
								;You're not in a good spot for climbing the wall.
							)
						else
							(HighPrint 64 3)
							;You would have trouble convincing people you are a Hero if you climbed the wall into town during the day.
						)
					)
					(
					(Said 'look,read/brick,marker,gravestone,epitaph[<grave]')
						(cond 
							((ego inRect: 130 113 168 140)
								(HighPrint 64 4)
								;The large tombstone seems to have a barely legible epitaph:
								(HighPrint 64 5)
								;"Here lies an atheist..."
								(HighPrint 64 6)
								;"All dressed up
								;and nowhere to go."
								(if (cast contains: mandrake)
									(HighPrint 64 7)
									;Growing up out of the grave is an evil-looking plant with a slimy, poisonous sheen.
									)
							)
							(
								(and
									(or (== (ego loop?) 2) (== (ego loop?) 6))
									(ego inRect: 28 168 112 189)
								)
								(HighPrint 64 8)
								;The large stone you are facing has been engraved with this message:
								(HighPrint 64 9)
								;"Here lies Lester Moore\nGored six times by a wild boar."
								(HighPrint 64 10)
								;"No Les, no Moore."
							)
							(else
								(HighPrint 64 11)
								;The carving on the gravestones, for the most part, seems to have been intentionally defaced and worn with time.
								)
						)
					)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(switch spell
								(DETMAGIC
									(if Night
										(HighPrint 64 12)
										;You sense what nobody needed to tell you:
										;SOMETHING is making those spirits fly around, and it isn't the wind.
									else
										(HighPrint 64 13)
										;You sense a spiritual coldness.
									)
								)
								(OPEN
									(HighPrint 64 14)
									;The only things that can be opened here are graves, and you don't want to open THOSE!
									)
								(else
									(HighPrint 64 15)
									;Your magical abilities are to no avail against the undead.
									)
							)
						)
					)
					((Said 'look>')
						(cond 
							(
							(or (Said '<down') (Said '/ground,chasm,grave,pit'))
								(if (ego inRect: 204 144 267 167)
									(HighPrint 64 16)
									;You can't see the bottom of the hole before you.
								else
									(HighPrint 64 17)
									;Nothing grows in the dirt of the old graveyard.  Some of the graves appear to have been disturbed.
								)
							)
							((Said '/ghost,ghoul')
								(if Night
									(switch (Random 0 1)
										(0
											(HighPrint 64 18)
											;You see transparent, decaying, writhing, slimy, undead fragments of undulating ectoplasm!
											)
										(1
											(HighPrint 64 19)
											;I DO believe in spooks!  I DO believe in spooks!  I DO, I DO, I DO believe in spooks!!
											)
									)
								else
									(HighPrint 64 20)
									;You recall from somewhere that the most likely time to find ghosts is at night.
								)
							)
							((Said '/grave')
								(HighPrint 64 21)
								;The graves seemed to be placed haphazardly in the tiny graveyard.  All appear to be old and poorly maintained.
								)
							((Said '/wall')
								(HighPrint 64 22)
								;The town wall looks more massive from this side.
								)
							((Said '/tree')
								(HighPrint 64 23)
								;There is a very large and very dead tree which hovers over the graveyard.
								(HighPrint 64 24)
								;The forest surrounds the graveyard on three sides.
								)
							((Said '/fence')
								(HighPrint 64 25)
								;You see the rotted remains of a picket fence.
								)
							((Said '/ladder')
								(HighPrint 64 26)
								;What at first appears to be a ladder turns out to be the rotted remains of a picket fence.
								)
							((Said '/root,mandrake,plant')
								(cond 
									((ego has: iMandrake)
										(HighPrint 64 27)
										;Let's see...root..root...
										;I know I put that root SOMEwhere!
										)
									((not (cast contains: mandrake))
										(HighPrint 64 28)
										;You don't see any particularly interesting roots.
										)
									((ego inRect: 130 113 168 140)
										(HighPrint 64 7)
										;Growing up out of the grave is an evil-looking plant with a slimy, poisonous sheen.
										)
									(else (HighPrint 64 29)
										;The red root growing out of one of the graves has a strange and evil appearance.
										)
								)
							)
							((Said '[<at,around][/!*,cemetery,cemetery]')
								(if Night
									(HighPrint 64 30)
									;It appears that the spirits frolic here during the dark hours.
								else
									(HighPrint 64 31)
									;It's certainly creepy here even during the daylight hours.
								)
							)
						)
					)
					((Said 'kill')
						(HighPrint 64 32)
						;Everything here is already dead.
						)
					((Said 'get>')
						(cond 
							((Said '/ghost')
								(HighPrint 64 33)
								;You don't get ghosts.  Ghosts get YOU!
								)
							((Said '/mandrake,root,plant')
								(cond 
									((ego has: iMandrake) (PrintAlreadyDoneThat))
									((not (cast contains: mandrake))
										(HighPrint 64 28)
										;You don't see any particularly interesting roots.
										)
									((ego inRect: 130 113 168 140) (ego setScript: getRoot))
									(else (PrintNotCloseEnough))
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance riseDownLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser
					posn: -65 105
					setCycle: Forward
					setLoop: 5
					setPri: 10
					setStep: 6 4
				)
				(if (Btst fGhostsAttack)
					(riser
						z: 25
						posn: (riser x?) (+ (riser y?) 25)
						setMotion: Follow ego 30
					)
				else
					(riser setMotion: MoveTo 255 117 self)
				)
			)
			(1
				(riser
					posn: (- (riser x?) 47) (+ (riser y?) 17)
					setStep: 3 2
					setLoop: 3
					cel: 4
					setMotion: MoveTo 240 150
					setCycle: BegLoop self
				)
			)
			(2
				(riser setCel: 0 setMotion: MoveTo 240 205 self)
			)
			(3
				(-- ghostCount)
				(riser stopUpd: setScript: 0)
			)
		)
	)
)

(instance riseUpLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser
					posn: 230 205
					setStep: 3 2
					setPri: 10
					setLoop: 4
					setCel: 0
					setMotion: MoveTo 230 150 self
				)
			)
			(1
				(riser setMotion: MoveTo 230 134 setCycle: EndLoop self)
			)
			(2
				(riser
					posn: (- (riser x?) 47) (- (riser y?) 17)
					setCycle: Forward
					setLoop: 6
					setPri: 9
					setStep: 6 4
				)
				(if (Btst fGhostsAttack)
					(riser
						z: 25
						posn: (riser x?) (+ (riser y?) 25)
						setMotion: Follow ego 30
					)
				else
					(riser setMotion: MoveTo -65 (Random 45 115) self)
				)
			)
			(3
				(-- ghostCount)
				(riser stopUpd: setScript: 0)
			)
		)
	)
)

(instance riseUpRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser
					posn: 240 205
					setStep: 3 2
					setPri: 10
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 240 150 self
				)
			)
			(1
				(riser setMotion: MoveTo 240 134 setCycle: EndLoop self)
			)
			(2
				(riser
					posn: (+ (riser x?) 47) (- (riser y?) 17)
					setCycle: Forward
					setLoop: 5
					setStep: 6 4
					setMotion: MoveTo 320 -27 self
				)
			)
			(3
				(-- ghostCount)
				(riser stopUpd: setScript: 0)
			)
		)
	)
)

(instance peekABoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(longOne startUpd: setCycle: Forward)
				(if (Btst fGhostsAttack)
					(longOne setMotion: Follow ego 30)
				else
					(longOne setMotion: MoveTo 21 70 self)
				)
			)
			(1
				(++ local0)
				(longOne setCycle: Reverse setMotion: MoveTo 21 50 self)
			)
			(2
				(if (> local0 2)
					(longOne setMotion: MoveTo 21 126 self)
				else
					(self changeState: 0)
				)
			)
			(3
				(-- ghostCount)
				(longOne stopUpd: setScript: 0)
			)
		)
	)
)

(instance getHimNorth of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(or
					(< (ego distanceTo: riser) 25)
					(< (ego distanceTo: (ScriptID GHOSTS 5)) 25)
					(< (ego distanceTo: (ScriptID GHOSTS 6)) 25)
				)
				(not ghostsDrainEgo)
			)
			(= ghostsDrainEgo TRUE)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser
					z: 35
					setPri: 14
					setLoop: 5
					posn: 52 116
					setCycle: Forward
					setMotion: Follow ego 20
				)
				((ScriptID GHOSTS 5)
					view: vGhosts
					setLoop: 0
					setPri: 14
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 163 0
					init:
					cycleSpeed: 1
					setCycle: Forward
					setStep: 3 3
					setMotion: Follow ego 20
				)
				((ScriptID GHOSTS 6)
					view: vGhosts
					setLoop: 8
					setPri: 7
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 251 39
					init:
					setCycle: Forward
					setStep: 3 2
					setMotion: Follow ego 20
				)
				(= cycles 5)
			)
			(1
				(if (not (Btst VISITED_GRAVEYARD_NIGHTTIME))
						(HighPrint 64 34)
						;It appears that you have company in the graveyard tonight.
						)
			)
			(2
				(HandsOff)
				(riser setMotion: Follow ego 5)
				((ScriptID GHOSTS 5) setMotion: Follow ego 5)
				((ScriptID GHOSTS 6) setMotion: Follow ego 5)
				(= cycles 15)
			)
			(3
				(ego
					view: vEgoDefeatedMagic
					setLoop: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(4 (LifeDrained))
		)
	)
)

(instance getHimSouth of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(or
					(< (ego distanceTo: riser) 25)
					(< (ego distanceTo: (ScriptID GHOSTS 7)) 25)
					(< (ego distanceTo: longOne) 25)
				)
				(not ghostsDrainEgo)
			)
			(= ghostsDrainEgo TRUE)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser setScript: riserGetsUp)
				(longOne
					setPri: 14
					posn: 59 221
					cycleSpeed: 1
					setCycle: Forward
					setMotion: Follow ego 5
				)
				((ScriptID GHOSTS 7)
					view: vGhosts
					setPri: 14
					setLoop: 1
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 27 53
					init:
					cycleSpeed: 1
					setCycle: Forward
					setStep: 3 2
					setMotion: Follow ego 5
				)
				(= cycles 5)
			)
			(1
				(if (not (Btst VISITED_GRAVEYARD_NIGHTTIME))
					(HighPrint 64 34)
					;It appears that you have company in the graveyard tonight.
					)
			)
			(2 (HandsOff) (= cycles 15))
			(3
				(ego
					view: vEgoDefeatedMagic
					setLoop: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(4 (LifeDrained))
		)
	)
)

(instance riserGetsUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(riser
					setLoop: 4
					setCel: 0
					setPri: 11
					posn: 234 203
					setMotion: MoveTo 234 163 self
				)
			)
			(1
				(riser setCel: -1 setCycle: EndLoop self)
			)
			(2
				(riser
					z: 35
					posn: (- (riser x?) 47) (+ (riser y?) 8)
					setPri: 14
					setLoop: 6
					setCycle: Forward
					setMotion: Follow ego 5
				)
			)
		)
	)
)

(instance gotHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(riser
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				((ScriptID GHOSTS 5)
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				((ScriptID GHOSTS 7)
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				((ScriptID GHOSTS 6)
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				(longOne
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				(= cycles 15)
			)
			(1
				(ego
					view: vEgoDefeatedMagic
					setLoop: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2 (LifeDrained))
		)
	)
)

(instance safeIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(and Night (Btst VISITED_GRAVEYARD_NIGHTTIME))
						(and (not Night) (Btst VISITED_GRAVEYARD_DAYTIME))
					)
					(client setScript: 0)
				else
					(= cycles 8)
				)
			)
			(1
				(HighPrint 64 35)
				;This must be the town graveyard.  What a run-down place!
				(if Night
					(HighPrint 64 30)
					;It appears that the spirits frolic here during the dark hours.
				else
					(HighPrint 64 31)
					;It's certainly creepy here even during the daylight hours.
				)
				(client setScript: 0)
			)
		)
	)
)

(instance fallInHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setCycle: 0
					setLoop: (ego loop?)
					setPri: 10
					setStep: 3 12
					illegalBits: 0
					setMotion: MoveTo (ego x?) 230 self
				)
			)
			(1
				(EgoDead 64 36
					#icon vDeathScenes 1 5
					#title { Watch your step!_})
					;You stepped into a hole whose depth is unfathomable.
					;As you fall ever downward, you feel icy fingers clutching at you, and you think: "How could I be so careless?"
			)
		)
	)
)

(instance getRoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 130 126 self
				)
			)
			(1
				(ego setLoop: 3)
				(mandrake dispose:)
				(= cycles 2)
			)
			(2
				(if (== timeODay TIME_MIDNIGHT)
					(HighPrint 64 37)
					;You wrench the mandrake plant free from the tombstone on which it has grown.
					;You hear a scream like that of a dying child as you yank the root from the ground.
					(SolvePuzzle POINTS_GETMANDRAKEROOT 6)
					(ego get: iMandrake)
				else
					(HighPrint 64 38)
					;You wrench the mandrake plant free from the tombstone on which it has grown.
					;Somehow you thought this would be a lot harder.  You wonder if you did this right.
					(HighPrint 64 39)
					;The root shrivels and twists in your hands until nothing is left.  Astonished, you look at your empty hands.
					(= mandrakeDay Day)
				)
				(ego setMotion: MoveTo 130 130 self)
			)
			(3
				(HandsOn)
				(NormalEgo)
				(ego setScript: 0)
			)
		)
	)
)

(instance ghostMusic of Sound
	(properties
		number 12
		loop -1
	)
)
