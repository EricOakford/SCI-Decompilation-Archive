;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh) (include "64.shm")
(use Main)
(use Procs)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Follow)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm64 0
)

(local
	local0
	local1
	local2
	local3
	twisterX
	twisterY
	theTwisterX
	theTwisterY
	local8
	local9
	local10
	cueGrave
	local12
	timesTalkedToGhosts
	cueGhostOil
	[local15 45] = [5 0 180 27 5 1 196 47 5 2 190 67 5 3 153 80 5 4 132 91 5 5 152 116 5 6 189 114 5 7 198 96 5 8 179 75 5 9 169 57 5 10 174 35 -32768]
)
(procedure (GhostsKillEgo)
	(EgoDead 84 85 0 0 516)
)

(instance rm64 of Room
	(properties
		noun N_ROOM
		picture 64
		style DISSOLVE
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						189
						254
						189
						264
						175
						318
						165
						319
						149
						224
						167
						204
						146
						210
						130
						182
						112
						152
						112
						191
						136
						181
						146
						0
						146
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 157 42 189 0 189
					yourself:
				)
		)
		(LoadMany RES_VIEW 63 516)
		(Load RES_SCRIPT 942)
		(super init:)
		(features
			add:
				crossStone
				graveStone
				grate
				theWall
				theSky
				theTrees
				theGrass
				theMortuary
		)
		(cSound fade:)
		(if
			(and
				(not (ego has: iMandrake))
				(< (+ mandrakeDay 1) Day)
			)
			(mandrake setPri: 8 stopUpd: init:)
		)
		(if
		(and Night (or (== timeODay TIME_MIDNIGHT) (== timeODay TIME_NOTYETDAWN)))
			(Load RES_SCRIPT REVERSE)
			(= deathMusic 37)
			(riser init: cycleSpeed: 24 setCycle: Forward)
			(longOne
				cycleSpeed: 18
				setLoop: 0
				setPri: 8
				illegalBits: 0
				ignoreActors:
				init:
				setScript: upThisTime
			)
			(longOneBottom
				cycleSpeed: 18
				setLoop: 2
				setPri: 8
				illegalBits: 0
				ignoreActors:
				init:
			)
			(twister
				init:
				setLoop: 6
				setPri: 8
				cycleSpeed: 12
				setScript: twistIt
			)
			(tumbler
				init:
				setLoop: 9
				setPri: 14
				illegalBits: 0
				ignoreActors:
				setCycle: EndLoop
				setScript: spinOnTree
			)
			(swimmer
				init:
				setLoop: 4
				setPri: 14
				ignoreActors:
				cycleSpeed: 12
				setScript: swimRight
			)
			(ghostMusic init: play:)
		)
		(= global272 0)
		(= local1 (Random 0 7))
		(NormalEgo)
		(ego
			init:
			setPri: 13
			ignoreActors:
			ignoreControl: -32768
			actions: unusualDoVerb
		)
		(if (and Night (Btst fGhostOil))
			(SolvePuzzle POINTS_USEUNDEADUNGUENT 2)
		)
		(switch prevRoomNum
			(72
				(ego posn: 160 188 setMotion: MoveTo 160 180)
				(if (and Night (not (Btst fGhostOil)))
					(= global272 5)
					(self setScript: gotHim)
				else
					(self setScript: safeIntro)
				)
			)
			(63
				(ego posn: 1 172)
				(self setScript: safeIntro)
			)
			(else  (ego posn: 44 177))
		)
	)
	
	(method (doit)
		(if
			(and
				(not (ego script?))
				(== (ego edgeHit?) 3)
				(not local3)
			)
			(ego setScript: walkOutTo72)
		)
		(if
			(and
				(not (ego script?))
				(not (== (curRoom script?) safeIntro))
				(< (ego x?) 5)
			)
			(ego setScript: walkTo63)
		)
		(cond 
			(
				(and
					(< 68 (ego x?))
					(< (ego x?) 270)
					(< 120 (ego y?))
					(< (ego y?) 180)
					(not local2)
					(not (Btst fGhostOil))
					(not (Btst fGhostsAttack))
					Night
				)
				(Bset fGhostsAttack)
				(= local9 1)
			)
			((Btst fGhostsAttack)
				(if
					(and
						(not local2)
						(or
							(< (ego distanceTo: riser) 35)
							(< (ego distanceTo: twister) 35)
							(< (ego distanceTo: swimmer) 35)
							(< (ego distanceTo: tumbler) 35)
							(< (ego distanceTo: longOne) 35)
						)
					)
					(= local2 1)
					(Bclr fGhostsAttack)
					(self setScript: gotHim)
				)
			)
			((and Night (== local12 0))
				(= local12 1)
				(Load RES_SCRIPT 969)
				(= deathMusic 37)
				(longOne
					cycleSpeed: 18
					setLoop: 0
					setPri: 8
					illegalBits: 0
					ignoreActors:
					init:
					setScript: upThisTime
				)
				(longOneBottom
					cycleSpeed: 18
					setLoop: 2
					setPri: 8
					illegalBits: 0
					ignoreActors:
					init:
				)
				(twister
					init:
					setLoop: 6
					setPri: 8
					cycleSpeed: 12
					setScript: twistIt
				)
				(tumbler
					init:
					setLoop: 9
					setPri: 14
					illegalBits: 0
					ignoreActors:
					setCycle: EndLoop
					setScript: spinOnTree
				)
				(swimmer
					init:
					setLoop: 4
					setPri: 14
					ignoreActors:
					cycleSpeed: 12
					setScript: swimRight
				)
				(ghostMusic init: play:)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(if Night
			(= deathMusic (SoundFX 26))
			(riser setMotion: 0 setCycle: 0 setScript: 0)
			(twister setMotion: 0 setCycle: 0 setScript: 0)
			(tumbler setMotion: 0 setCycle: 0 setScript: 0)
			(longOne setMotion: 0 setCycle: 0 setScript: 0)
			(longOneBottom setMotion: 0 setCycle: 0 setScript: 0)
			(swimmer setMotion: 0 setCycle: 0 setScript: 0)
			(Bset fBeenInGraveyardNight)
		else
			(Bset VISITED_GRAVEYARD_DAYTIME)
		)
		(Bset VISITED_GRAVEYARD_DAYTIME)
		(Bclr fGhostsAttack)
		(DisposeScript MOVECYC)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 3)
					(1 (messager say: N_ROOM V_LOOK C_LOOK1))
					(2 (messager say: N_ROOM V_LOOK C_LOOK2))
					(3
						(if Night
							(messager say: N_ROOM V_LOOK C_LOOKNIGHT)
						else
							(messager say: N_ROOM V_LOOK C_LOOKDAY)
						)
					)
				)
			)
			;these don't seem to trigger for some reason
			(V_DETECT
				(if Night
					(messager say: N_ROOM V_DETECT C_NIGHT)
				else
					(messager say: N_ROOM V_DETECT)
				)
			)
			;added in unused message
			(V_FETCH
				(messager say: N_ROOM V_FETCH)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch cueGrave
			(1
				(= cueGrave 2)
				(messager say: N_GRAVESTONE V_LOOK 0 2 self)
			)
			(2
				(= cueGrave 3)
				(messager say: N_GRAVESTONE V_LOOK 0 3 self)
			)
			(3
				(if (cast contains: mandrake)
					(messager say: N_GRAVESTONE V_LOOK C_ROOT)
				else
					(messager say: N_GRAVESTONE V_LOOK C_NOROOT)
				)
			)
			(4
				(= cueGrave 5)
				(messager say: N_CROSS_STONE V_LOOK 0 2 self)
			)
			(5 (messager say: N_CROSS_STONE V_LOOK 0 3))
			(6 (messager say: N_ROOM 0 9 2))
		)
	)
)

(instance unusualDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_GHOSTOIL)
				(Bset fGhostOil)
				(Bclr fGhostsAttack)
				(= ghostOilTimer 150)
				(if Night
					(SolvePuzzle POINTS_USEUNDEADUNGUENT 2)
				)
				(messager say: N_ROOM 83 0)
				(= cueGhostOil 1)
				(ego setScript: cueItScript)
			else
				(return FALSE)
			)
		)
	)
)

(instance theGrass of Feature
	(properties
		x 151
		y 6
		noun N_GRASS
		sightAngle 40
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if Night
				(messager say: N_GRASS V_LOOK)
			else
				(messager say: N_GRASS V_LOOK C_DAY)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theTrees of Feature
	(properties
		x 151
		y 6
		noun N_TREES
		sightAngle 40
		onMeCheck $0002
	)
)

(instance theSky of Feature
	(properties
		x 151
		y 6
		noun N_SKY
		sightAngle 40
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if Night
				(messager say: N_SKY V_LOOK 0)
			else
				(messager say: N_SKY V_LOOK C_DAY)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theWall of Feature
	(properties
		x 303
		y 29
		z 71
		noun N_WALL
		nsTop 29
		nsLeft 304
		nsBottom 118
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if Night
					(if (TrySkill CLIMB 35 0)
						(ego setScript: upTheWall)
					else
						(messager say: N_WALL V_DO 0)
					)
				else
					(messager say: N_WALL V_DO C_DAY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theMortuary of Feature
	(properties
		x 134
		y 8
		z 93
		noun N_MORTUARY
		nsTop 8
		nsLeft 44
		nsBottom 74
		nsRight 224
		sightAngle 40
	)
)

(instance graveStone of Feature
	(properties
		x 273
		y 50
		noun N_GRAVESTONE
		nsTop 50
		nsLeft 243
		nsBottom 143
		nsRight 304
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= cueGrave 1)
				(messager say: N_GRAVESTONE V_LOOK 0 1 curRoom)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance crossStone of Feature
	(properties
		x 65
		y 103
		noun N_CROSS_STONE
		nsTop 103
		nsLeft 46
		nsBottom 131
		nsRight 84
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(= cueGrave 4)
			(messager say: N_CROSS_STONE V_LOOK 0 1 curRoom)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance grate of Feature
	(properties
		x 151
		y 93
		noun N_GRATE
		nsTop 80
		nsLeft 128
		nsBottom 107
		nsRight 174
		sightAngle 40
	)
)

(instance mandrake of View
	(properties
		x 214
		y 150
		noun N_MANDRAKE
		view 63
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (ego inRect: 192 134 243 160)
					(messager say: N_MANDRAKE V_LOOK 0)
				else
					(messager say: N_MANDRAKE V_LOOK C_NEARMANDRAKE)
				)
			)
			(V_DO (ego setScript: getRoot))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance riser of Actor
	(properties
		x 75
		y 145
		noun N_GHOST
		view 64
		loop 7
		signal $6000
		illegalBits $0000
	)
	
	(method (init)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(switch (Random 0 1)
						(0 (messager say: N_GHOST V_LOOK C_LOOKGHOST1))
						(1 (messager say: N_GHOST V_LOOK C_LOOKGHOST2))
					)
				else
					(messager say: N_GHOST V_LOOK C_NOGHOST)
				)
			)
			(V_TALK
				(ego setScript: talkToGhosts)
			)
			(V_DO (messager say: N_GHOST V_DO 0 2))
			(V_DAGGER (messager say: N_GHOST V_DAGGER 0 2))
			(V_ROCK (messager say: N_GHOST V_ROCK 0 2))
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance twister of Actor
	(properties
		x 259
		y 125
		noun N_GHOST
		view 64
		loop 6
		cel 3
		signal $6000
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(riser doVerb: theVerb &rest)
	)
)

(instance longOne of Actor
	(properties
		x 268
		y 102
		noun N_GHOST
		view 64
		signal $6000
		illegalBits $0000
	)
	
	(method (init)
		(= nightPalette 164)
		(PalVary PALVARYTARGET 164)
		(kernel_128 64)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(riser doVerb: theVerb &rest)
	)
)

(instance longOneBottom of Actor
	(properties
		x 269
		y 135
		noun N_GHOST
		view 64
		loop 2
		signal $6000
		illegalBits $0000
	)
	
	(method (doit)
		(longOneBottom
			posn: (+ (longOne x?) 1) (+ (longOne y?) 33)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(riser doVerb: theVerb &rest)
	)
)

(instance tumbler of Actor
	(properties
		x 168
		y 77
		noun N_GHOST
		view 64
		loop 9
		signal $6000
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(riser doVerb: theVerb &rest)
	)
)

(instance swimmer of Actor
	(properties
		x 200
		y 180
		noun N_GHOST
		view 64
		loop 4
		signal $6000
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(riser doVerb: theVerb &rest)
	)
)

(instance upTheWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 275 153 self)
			)
			(1
				(ego setMotion: MoveTo 313 150 self)
			)
			(2
				(ego
					view: 517
					setLoop: 0
					cel: 0
					posn: 313 120
					ignoreHorizon:
				)
				(self cue:)
			)
			(3
				(if (TrySkill CLIMB 35 0)
					(ego setCycle: Forward setMotion: MoveTo 313 42 self)
				else
					(ego setScript: notGoodEnough)
				)
			)
			(4
				(ego loop: 1 cel: 0 posn: 312 24 setCycle: EndLoop self)
			)
			(5 (curRoom newRoom: 330))
		)
	)
)

(instance notGoodEnough of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: Forward setMotion: MoveTo 313 100 self)
			)
			(1
				(ego setCycle: 0 setCel: 0 setMotion: MoveTo 313 120 self)
			)
			(2
				(messager say: N_ROOM 0 0 1)
				(NormalEgo)
				(ego view: 0 posn: 313 150)
				(self cue:)
			)
			(3
				(ego setMotion: MoveTo 200 167 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance gotHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(riser setPri: 7 cycleSpeed: 24 setCycle: Forward)
				(twister
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				(swimmer
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				(tumbler
					setPri: (+ (ego priority?) 1)
					setMotion: Follow ego 10
				)
				(longOne setScript: peekABoo)
				(ego setMotion: 0)
				(= ticks 360)
			)
			(1
				(ego view: 516 setLoop: 0 setCycle: EndLoop self)
			)
			(2 (GhostsKillEgo))
		)
	)
)

(instance safeIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== prevRoomNum 63)
					(ego setMotion: MoveTo 44 177 self)
				else
					(self cue:)
				)
			)
			(1
				(cond 
					(
						(or
							(== prevRoomNum 63)
							(and Night (Btst fBeenInGraveyardNight))
							(and (not Night) (Btst VISITED_GRAVEYARD_DAYTIME))
						)
						(= ticks 60)
					)
					((and (> timeODay TIME_NIGHT) (not (Btst fGhostOil))) (client setScript: gotHim))
					(else (= seconds 1))
				)
			)
			(2
				(cond 
					((Btst VISITED_GRAVEYARD_DAYTIME) (self cue:))
					(Night (self cue:))
					(else (messager say: N_ROOM 0 0 2 self))
				)
			)
			(3
				(cond 
					(Night (messager say: N_ROOM 0 C_NIGHT 1 self))
					((Btst VISITED_GRAVEYARD_DAYTIME) (self cue:))
					(else (messager say: N_ROOM 0 C_FIRST_TIME 1 self))
				)
			)
			(4 (HandsOn) (self dispose:))
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
					setPri: 13
					setMotion: MoveTo 214 165 self
				)
			)
			(1
				(if (== timeODay TIME_MIDNIGHT) (ego get: iMandrake))
				(ego setLoop: 3)
				(mandrake dispose:)
				(= cycles 2)
			)
			(2
				(if (== timeODay TIME_MIDNIGHT)
					(messager say: N_ROOM 0 C_MIDNIGHT)
					(SolvePuzzle POINTS_GETMANDRAKEROOT 6)
				else
					(= cueGrave 6)
					(messager say: N_ROOM 0 C_WRONGTIME 1 curRoom)
					(= mandrakeDay Day)
				)
				(NormalEgo)
				(ego setPri: pLMAGENTA setMotion: MoveTo 185 (mandrake y?) self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance twistIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local10 1)
				(twister
					setLoop: 6
					setCel: local10
					setPri: 12
					ignoreActors:
					illegalBits: 0
					startUpd:
					setMotion: MoveTo 216 101 self
				)
			)
			(1
				(twister setMotion: MoveTo 277 54 self)
				(= twisterX (twister x?))
				(= twisterY (twister y?))
			)
			(2
				(++ local10)
				(= theTwisterX twisterX)
				(= theTwisterY twisterY)
				(= twisterX (Random 20 210))
				(= twisterY (Random 54 130))
				(twister setCycle: CycleTo local10 1 self)
				(if local9
					(twister setMotion: Follow ego 30)
				else
					(twister setMotion: MoveTo twisterX twisterY self)
				)
			)
			(3
				(if (> local10 8) (= local10 0))
				(self cue:)
			)
			(4
				(-- global272)
				(= ticks 300)
			)
			(5 (self changeState: 2))
		)
	)
)

(instance swimRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer
					view: 64
					setLoop: 4
					setPri: 14
					ignoreActors:
					illegalBits: 0
					setCycle: Forward
				)
				(= ticks 30)
			)
			(1
				(if local9
					(swimmer setMotion: Follow ego 30)
				else
					(swimmer
						setMotion: MoveTo (Random 110 210) (Random 140 187) self
					)
				)
			)
			(2
				(-- global272)
				(switch (Random 1 2)
					(1
						(swimmer setCycle: 0 setMotion: 0 setLoop: 4 setCel: 4)
					)
					(2
						(swimmer setMotion: MoveTo (+ (ego x?) 100) (ego y?))
					)
				)
				(= ticks (Random 60 120))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance peekABoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(longOneBottom cel: 0 setPri: 15 setCycle: Forward)
				(longOne
					posn: 268 102
					setLoop: 0
					startUpd:
					setPri: 15
					setCel: 0
					setCycle: EndLoop
					setMotion: Follow ego 30
				)
			)
		)
	)
)

(instance upThisTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(longOne
					posn: 153 108
					setCel: 4
					setLoop: 0
					setMotion: MoveTo 148 26 self
				)
				(longOneBottom setLoop: 2)
			)
			(1 (= ticks (Random 180 240)))
			(2
				(longOne cel: 0 setCycle: EndLoop self)
			)
			(3
				(longOne setCycle: CycleTo 4 1 setMotion: MoveTo 153 118 self)
			)
			(4 (= ticks (Random 120 180)))
			(5
				(switch (Random 1 2)
					(1 (longOne setScript: upThird))
					(2
						(longOne setScript: upSecond)
					)
				)
			)
		)
	)
)

(instance upSecond of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(longOne
					posn: 270 87
					setCel: 4
					setCycle: EndLoop
					setMotion: MoveTo 273 23 self
				)
			)
			(1 (= ticks (Random 180 240)))
			(2
				(longOne cel: 0 setCycle: EndLoop self)
			)
			(3
				(longOne setCycle: BegLoop setMotion: MoveTo 270 87 self)
			)
			(4 (= ticks (Random 120 240)))
			(5
				(switch (Random 1 2)
					(1 (longOne setScript: upThird))
					(2
						(longOne setScript: upThisTime)
					)
				)
			)
		)
	)
)

(instance upThird of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(longOne
					posn: 69 82
					setCel: 4
					setMotion: MoveTo 77 21 self
				)
			)
			(1 (= ticks (Random 180 240)))
			(2
				(longOne cel: 0 setCycle: EndLoop self)
			)
			(3
				(longOne setCycle: CycleTo 4 1 setMotion: MoveTo 69 82 self)
			)
			(4 (= ticks (Random 60 240)))
			(5
				(switch (Random 1 2)
					(1
						(longOne setScript: upSecond)
					)
					(2
						(longOne setScript: upThisTime)
					)
				)
			)
		)
	)
)

(instance spinOnTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tumbler
					ignoreActors:
					illegalBits: 0
					posn: 168 77
					setLoop: 9
					setCel: 0
					show:
					cycleSpeed: 9
					setCycle: EndLoop self
				)
			)
			(1
				(++ local8)
				(tumbler
					setLoop: 5
					posn: 180 27
					cycleSpeed: 12
					setCel: 0
					setCycle: MoveCycle @local15 tumbler
				)
				(if (and (== (ego x?) 174) (== (ego y?) 35))
					(self cue:)
				)
			)
			(2
				(tumbler setCel: 11 posn: 159 14)
				(if local9
					(tumbler setMotion: Follow ego 30)
				else
					(self cue:)
				)
			)
			(3
				(tumbler
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					setMotion: MoveTo 173 (- (tumbler y?) 18) self
				)
			)
			(4
				(-- global272)
				(tumbler
					posn: (- (tumbler x?) 3) (- (tumbler y?) 5)
					setCel: 12
					setMotion: MoveTo 179 (- (tumbler y?) 11) self
				)
			)
			(5
				(tumbler
					posn: 186 (+ (tumbler y?) 5)
					setCel: 13
					setMotion: MoveTo 202 (- (tumbler y?) 20) self
				)
			)
			(6
				(tumbler hide:)
				(= ticks (Random 360 1000))
			)
			(7 (self changeState: 0))
		)
	)
)

(instance talkToGhosts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ timesTalkedToGhosts)
				(self cue:)
			)
			(1
				(switch timesTalkedToGhosts
					(1
						(messager say: N_GHOST V_ALTTALK 0 1 self)
					)
					(2
						(messager say: N_GHOST V_ALTTALK 0 2 self)
					)
					(3
						(messager say: N_GHOST V_ALTTALK 0 3 self)
					)
					(4
						(messager say: N_GHOST V_ALTTALK 0 4 self)
					)
					(5
						(messager say: N_GHOST V_ALTTALK 0 5 self)
					)
					(6
						(messager say: N_GHOST V_ALTTALK 0 6 self)
					)
				)
			)
			(2
				(if (== timesTalkedToGhosts 6) (= timesTalkedToGhosts 0))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkOutTo72 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1 (curRoom newRoom: 72))
		)
	)
)

(instance walkTo63 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1 (curRoom newRoom: 63))
		)
	)
)

(instance cueItScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch cueGhostOil
					(1 (ego use: iGhostOil) (ego get: iFlask))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance ghostMusic of Sound
	(properties
		number 12
		loop -1
	)
)
