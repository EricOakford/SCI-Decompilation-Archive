;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh) (include "321.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm321 0
)

(enum 1
	cueComeOnIn
	cueFaceTheMusic
	cueRight
)

(local
	savePri
	safeCrackSuccess
	local2
	egoUpstairs
	vaseOutOfWay
	safeRevealed
	safeOpen
	toX
	toY
	hearTheDoor
	sheriffCue
	saveMoveSpeed
	saveCycleSpeed
	safeCrackTries
)
(instance rm321 of Room
	(properties
		noun N_ROOM
		picture 321
		style WIPERIGHT
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						302 189
						226 155
						227 143
						202 142
						191 137
						136 142
						129 151
						81 155
						76 169
						50 170
						25 102
						102 52
						190 52
						190 50
						101 50
						20 102
						44 170
						3 173
						3 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						221 175
						169 185
						147 177
						151 160
						195 151
						220 158
					yourself:
				)
		)
		(LoadMany RES_SCRIPT REVERSE DPATH)
		(LoadMany RES_VIEW 321 602 601 525 507 503)
		(LoadMany RES_SOUND 8 13 83
			(SoundFX 52)
			(SoundFX 35)
		)
		(super init:)
		(SolvePuzzle f321EnterSheriffHouse 5 THIEF)
		(self
			setFeatures:
				onDesk
				onSafe
				onPlant
				onChair
				gryphonHead
				firePicture
				stairsPicture
		)
		;UPGRADE
;;;		(onDesk init:)
;;;		(onSafe
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
;;;		(onPlant init:)
;;;		(onChair init:)
;;;		(gryphonHead init:)
;;;		(firePicture init:)
;;;		(stairsPicture init:)
		
		(self setRegions: TOWN)
		(sneakMusic init:)
		(tumbleMusic init:)
		(miscMusic init:)
		(= deathMusic (SoundFX sDeathBusted))
		(if (Btst fCrackedSafe)
			(= safeOpen TRUE)
		)
		(if (Btst fUncoveredSafe)
			(= safeRevealed TRUE)
		)
		(if (Btst fStoleVase)
			(= vaseOutOfWay TRUE)
		)
		(NormalEgo)
		(ego
			posn: 163 188
			illegalBits: -30720
			init:
			setScript: comeOnInScript
		)
		(fireStone init: setCycle: Forward)
		(fireBurns init: setCycle: Forward)
		(chairBurns init: ignoreActors: setCycle: Forward)
		(chestDrawer init: stopUpd:)
		(leftDoor ignoreActors: init: stopUpd:)
		(rightDoor ignoreActors: init: stopUpd:)
		(bottomDoor ignoreActors: init: stopUpd:)
		(if (not (Btst fStoleVase))
			(theVase
				illegalBits: 0
				ignoreActors:
				posn: (if (Btst fMovedVase) 224 else 228) (if (Btst fMovedVase) 150 else 120)
				setPri: 10
				init:
				stopUpd:
			)
		)
		(portrait
			posn:
				(if (not (Btst fUncoveredSafe)) 229 else 238)
				(if (not (Btst fUncoveredSafe)) 104 else 88)
			init:
			stopUpd:
		)
		(if (not (Btst fStoleCandelabra))
			(candelabra setPri: 9 init: stopUpd:)
		)
		(if (not (Btst fStoleMusicBox))
			(musicBox init: stopUpd:)
		)
		(sneakMusic play:)
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			((and (== (ego edgeHit?) SOUTH) (not (Btst fOttoAwakened)))
				(if Night
					(= daySheriffBreakIn Day)
					(curRoom newRoom: 320)
				else
				;reversed the loop and cel to allow the death icon (Otto squishing the Hero) to appear.
					(EgoDead 20 21 0 4 602)
				)
			)
			(
				(and
					(ego inRect: 26 165 69 168)
					(not egoUpstairs)
					(not (Btst fWokeUpSheriff))
					(or (== (ego loop?) 7) (== (ego loop?) 3))
				)
				(= egoUpstairs TRUE)
				(ego setScript: upScript)
			)
			(
				(and
					egoUpstairs
					(ego inRect: 92 48 121 55)
					(not (Btst fWokeUpSheriff))
					(or (== (ego loop?) 5) (== (ego loop?) 1))
				)
				(= egoUpstairs FALSE)
				(ego setScript: downScript)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Bclr fOttoAwakened)
		(Bset fBeenIn321)
		(DisposeScript DPATH)
		(DisposeScript MOVECYC)
		(= deathMusic (SoundFX sDeath))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(curRoom setScript: bustedScript)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(switch sheriffCue
			(cueComeOnIn
				(messager say: N_ROOM NULL NULL 2)
			)
			(cueFaceTheMusic
				(messager say: N_ROOM NULL NULL 5)
			)
			(cueRight
				(messager say: N_ROOM NULL NULL 15)
			)
		)
	)
)

(instance onOpenSafe of Feature
	(properties
		x 229
		y 158
		noun N_SAFE
		nsTop 86
		nsLeft 224
		nsBottom 100
		nsRight 237
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((not safeOpen)
						(messager say: N_SAFE V_LOOK C_LOOTSAFE)
					)
					((Btst fSearchedSafe)
						(messager say: N_SAFE V_LOOK C_SAFEEMPTY)
					)
					(else
						(messager say: N_SAFE V_LOOK C_MYMISTAKE)
					)
				)
			)
			(V_DO
				(ego setScript: toTheSafe)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onDesk of Feature
	(properties
		x 81
		y 134
		noun N_DESK
		nsTop 122
		nsLeft 65
		nsBottom 146
		nsRight 98
		sightAngle 40
		approachX 89
		approachY 157
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fSearchedDrawer)
					(messager say: N_DESK V_LOOK C_DRAWERLOOTED)
				else
					(messager say: N_DESK V_LOOK C_DRAWERNOTLOOTED)
				)
			)
			(V_DO
				(ego setScript: toTheDesk)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onSafe of Feature
	(properties
		x 229
		y 158
		z 40
		noun N_SAFE
		nsTop 87
		nsLeft 224
		nsBottom 101
		nsRight 235
		sightAngle 40
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((not safeOpen)
						(messager say: N_SAFE V_LOOK C_LOOTSAFE)
					)
					((Btst fSearchedSafe)
						(messager say: N_SAFE V_LOOK C_SAFEEMPTY)
					)
					(else
						(messager say: N_SAFE V_LOOK C_MYMISTAKE)
					)
				)
			)
			(V_DO
				(ego setScript: toTheSafe)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onPlant of Feature
	(properties
		x 11
		y 166
		noun N_PLANT
		nsTop 147
		nsBottom 186
		nsRight 22
		sightAngle 40
	)
)

(instance onChair of Feature
	(properties
		x 173
		y 157
		noun N_CHAIR
		nsTop 140
		nsLeft 161
		nsBottom 174
		nsRight 185
		sightAngle 40
	)
)

(instance onTable of Feature
	(properties
		x 197
		y 161
		noun N_TABLE
		nsTop 154
		nsLeft 185
		nsBottom 169
		nsRight 210
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fStoleMusicBox)
					(messager say: N_TABLE V_LOOK C_TOOKMUSICBOX)
				else
					(messager say: N_TABLE V_LOOK C_MUSICBOXONTABLE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gryphonHead of Feature
	(properties
		x 272
		y 161
		z 86
		noun N_GRYPHONHEAD
		nsTop 56
		nsLeft 251
		nsBottom 95
		nsRight 294
		sightAngle 40
	)
)

(instance firePicture of Feature
	(properties
		x 284
		y 167
		z 136
		noun N_FIREPICTURE
		nsLeft 251
		nsBottom 62
		nsRight 318
		sightAngle 40
	)
)

(instance stairsPicture of Feature
	(properties
		x 37
		y 21
		noun N_STAIRSPICTURE
		nsBottom 42
		nsRight 74
		sightAngle 40
	)
)

(instance portrait of View
	(properties
		noun N_PORTRAIT
		approachX 219
		approachY 145
		view 321
		loop 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setScript: toThePortrait)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance candelabra of View
	(properties
		x 81
		y 125
		noun N_CANDELABRA
		approachX 81
		approachY 130
		view 321
		loop 4
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: toTheCandelabra)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance musicBox of View
	(properties
		x 198
		y 158
		noun N_MUSICBOX
		approachX 207
		approachY 174
		view 321
		loop 4
		cel 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: toTheBox)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance safeDoor of Prop
	(properties
		x 224
		y 88
		noun N_SAFEDOOR
		view 321
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((not safeOpen)
						(messager say: N_SAFEDOOR V_LOOK C_LOOTSAFE)
					)
					((Btst fSearchedSafe)
						(messager say: N_SAFEDOOR V_LOOK C_SAFEEMPTY)
					)
					(else
						(messager say: N_SAFEDOOR V_LOOK C_LOOKINSAFE)
					)
				)
			)
			(V_DO
				(ego setScript: toTheSafe)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fireBurns of Prop
	(properties
		x 272
		y 160
		noun N_FIREBURNS
		view 321
		loop 8
		signal ignrAct
		detailLevel 2
	)
)

(instance chairBurns of Prop
	(properties
		x 172
		y 179
		noun N_SHADOW
		view 321
		loop 10
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance fireStone of Prop
	(properties
		x 261
		y 168
		noun N_FIRESTONE
		view 321
		loop 9
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance egoHead of Prop)

(instance chestDrawer of Prop
	(properties
		x 72
		y 130
		noun N_DRAWER
		approachX 89
		approachY 157
		view 321
		loop 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fSearchedDrawer)
					(messager say: N_DRAWER V_LOOK C_DRAWERLOOTED)
				else
					(messager say: N_DRAWER V_LOOK C_DRAWERNOTLOOTED)
				)
			)
			(V_DO
				(ego setScript: toTheDesk)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leftDoor of Prop
	(properties
		x 102
		y 47
		view 321
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= hearTheDoor TRUE)
				(ego setScript: toTheLeftDoor)
			)
			(V_DO
				(ego setScript: toTheLeftDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rightDoor of Prop
	(properties
		x 148
		y 47
		approachX 154
		approachY 50
		view 321
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= hearTheDoor TRUE)
				(ego setScript: toTheRightDoor)
			)
			(V_DO
				(ego setScript: toTheRightDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bottomDoor of Prop
	(properties
		x 146
		y 132
		approachX 148
		approachY 140
		view 321
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= hearTheDoor TRUE)
				(messager say: N_ROOM NULL NULL 23 self)
				(= hearTheDoor FALSE)
			)
			(V_DO
				(ego setScript: toTheBottomDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stars of Prop)

(instance pillow of Prop)

(instance otto of Actor)

(instance sheriff of Actor)

(instance theVase of Actor
	(properties
		z 10
		noun N_VASE
		approachX 219
		approachY 145
		view 321
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: toTheVase)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gerta of Actor) ;Gerta is the Sheriff's wife

(instance comeOnInScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor)
				(ego setMotion: PolyPath 163 169 self)
			)
			(1
				(if (and (!= prevRoomNum 0) (not (Btst fBeenIn321)))
					(= cycles 8)
				else
					(HandsOn)
					(ego setScript: 0)
				)
			)
			(2
				(= sheriffCue cueComeOnIn)
				(messager say: N_ROOM NULL NULL 1 curRoom)
				(= seconds 3)
			)
			(3
				(HandsOn)
				(ego setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance faceTheMusicScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fOttoAwakened)
				(messager say: N_ROOM NULL NULL 3 self)
			)
			(1
				(musicBox setCel: 3)
				(Bset fOpenMusicBox)
				(miscMusic loop: 1 number: 13 play:)
				(otto
					view: 602
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: Walk
					stopUpd:
				)
				(= sheriffCue cueFaceTheMusic)
				(messager say: N_ROOM NULL NULL 4 curRoom)
				(= seconds 3)
			)
			(2
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 10))
				(bottomDoor setCycle: EndLoop self)
			)
			(3
				(ego setHeading: 0)
				(otto setLoop: 1 cel: 1 posn: 178 120 setPri: 8)
				(self cue:)
			)
			(4
				(ego loop: 1)
				(otto setMotion: MoveTo 191 154 self)
			)
			(5
				(otto setLoop: 5 cel: 0 setCycle: Forward)
				(= cycles 20)
			)
			(6
				(cond 
					((ego has: iMusicBox)
						(messager say: N_ROOM NULL C_TOOKMUSICBOX)
					)
					((Btst fOpenMusicBox)
						(miscMusic stop:)
						(messager say: N_ROOM NULL C_MUSICBOXOPEN)
						(musicBox setCel: 2 forceUpd:)
						(Bclr fOpenMusicBox)
					)
					(else
						(messager say: N_ROOM NULL C_MUSICBOXCLOSED)
					)
				)
				(otto
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 178 136 self
				)
			)
			(7
				(otto dispose:)
				(bottomDoor setCycle: BegLoop)
				(ego setMotion: MoveTo 217 179 self)
			)
			(8
				(messager say: N_ROOM 0 0 6)
				(Bclr fOttoAwakened)
				(bottomDoor stopUpd:)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
			(9
				(sneakMusic stop:)
				(EgoDead 88 89 1 0 503)	;Can't get caught here, as the Hero always moves out of Otto's path.
			)
		)
	)
)

(instance vaseDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL NULL 7 self)
			)
			(1
				(Bset fMovedVase)
				(= vaseOutOfWay TRUE)
				(theVase posn: 224 150)
				(self cue:)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance vaseScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LoadMany RES_SOUND 14)
				(messager say: N_ROOM NULL NULL 8)
				(theVase startUpd: setCycle: EndLoop self)
			)
			(1 (self cue:))
			(2
				(theVase
					setLoop: 6
					setCel: 0
					posn: 228 125
					setStep: 0 8
					setMotion: MoveTo 223 148 self
				)
			)
			(3
				(miscMusic loop: 1 number: (SoundFX 14) play:)
				(theVase setCycle: EndLoop self)
			)
			(4
				(theVase setCel: 5 stopUpd:)
				(HandsOn)
				(= cycles 2)
			)
			(5
				(messager say: N_ROOM NULL NULL 9 self)
			)
			(6
				(ego setScript: bustedScript)
			)
		)
	)
)

(instance bustedScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(User canInput: FALSE)
				(LoadMany RES_SOUND 15)
				(otto
					view: 602
					setLoop: 1
					cel: 1
					posn: 170 131
					illegalBits: 0
					ignoreActors:
					setCycle: Walk
					setPri: 8
					init:
				)
				(sheriff
					view: 601
					illegalBits: 0
					ignoreActors:
					setLoop: 2
					cel: 0
					posn: 117 48
					setCycle: Walk
					init:
				)
				(bottomDoor
					loop: 7
					cel: 0
					posn: 146 133
					setPri: 9
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(miscMusic loop: 1 number: (SoundFX 15) play:)
				(otto setPri: 10 setMotion: MoveTo 191 154 self)
				(leftDoor setCycle: EndLoop)
			)
			(2
				(sheriff setMotion: MoveTo 115 50 self)
			)
			(3
				(cond 
					((> safeCrackTries 2)
						(EgoDead 19 22 0 4 602)
					)
					((== safeCrackSuccess 2)
						(sneakMusic stop:)
						(EgoDead 145 146 1 0 503)
					)
					;Loop and cel should be switched to show correct death icon.
					;However, it doesn't match the palette correctly.
					(else
						(sneakMusic stop:)
						(messager say: N_ROOM NULL NULL 10 self)
					)
				)
			)
			(4
				(= seconds 1)
			)
			(5
				(EgoDead 19 22 0 4 602)
			)
		)
	)
)

(instance openSafeDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fSearchedSafe))
					(ego get: iSilver 50)
				)
				(HandsOff)
				(miscMusic number: (SoundFX 35) loop: 1 play:)
				(if (not (Btst fCrackedSafe))
					(safeDoor setCycle: EndLoop self)
				else
					(= seconds 2)
				)
			)
			(1
				(SolvePuzzle f321CrackSafe 1 THIEF)
				(if (not (Btst fCrackedSafe))
					(++ safeCrackSuccess)
					(onOpenSafe init:)
					(messager say: N_ROOM NULL NULL 24 self)
				)
			)
			(2
				(= seconds 2)
			)
			(3
				(if (Btst fSearchedSafe)
					(messager say: N_ROOM NULL C_SAFEEMPTY 1 self)
				else
					(messager say: N_ROOM NULL C_FINDCOINBAG 1 self)
				)
			)
			(4
				(Bset fCrackedSafe)
				(= safeOpen TRUE)
				(= ticks 30)
			)
			(5
				(if (not (Btst fSearchedSafe))
					(Bset fSearchedSafe)
					(SolvePuzzle f321LootSafe 1 THIEF)
					(messager say: N_ROOM NULL C_LOOTSAFE 1 self)
				else
					(self cue:)
				)
			)
			(6
				(HandsOn)
				(ego cycleSpeed: saveCycleSpeed moveSpeed: saveMoveSpeed)
				(self dispose:)
			)
		)
	)
)

(instance raisePainting of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(safeDoor cel: (if (Btst fCrackedSafe) 1 else 0) init: stopUpd:)
				(portrait posn: 238 88)
				(Bset fUncoveredSafe)
				(= cycles 2)
			)
			(1
				(messager say: N_ROOM NULL NULL 11 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance robDesk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: iSilver 3)
				(HandsOff)
				(chestDrawer setCycle: EndLoop self)
			)
			(1
				(Bset fSearchedDrawer)
				(messager say: N_ROOM NULL NULL 12 self)
			)
			(2
				(SolvePuzzle f321SearchDrawer 1 THIEF)
				(chestDrawer setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lowerPainting of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(portrait posn: 229 104)
				(Bclr fUncoveredSafe)
				(= cycles 2)
			)
			(1
				(messager say: N_ROOM NULL NULL 13 self)
			)
			(2
				(HandsOn)
				(safeDoor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance bottomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(otto
					view: 602
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: Walk
					stopUpd:
				)
				(ego setMotion: MoveTo 186 140 self)
			)
			(1
				(otto setLoop: 1 posn: 170 131)
				(bottomDoor setCycle: EndLoop self)
			)
			(2
				(ego
					setLoop: 3
					setCycle: Reverse
					setMotion: MoveTo 196 142 self
				)
			)
			(3
				(ego setCycle: 0 setHeading: 270)
				(NormalEgo)
				(= ticks 30)
			)
			(4
				(otto setMotion: MoveTo fStoleCandelabra 134 self)
			)
			(5
				(= ticks 60)
			)
			(6
				(sneakMusic stop:)
				(EgoDead 98 99 0 4 602)
			)
		)
	)
)

(instance leftScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 ignoreActors:)
				(self cue:)
			)
			(1
				(ego setMotion: MoveTo 125 50 self)
			)
			(2
				(sheriff
					view: 601
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: Walk
					stopUpd:
				)
				(leftDoor setCycle: EndLoop self)
			)
			(3
				(sheriff
					setLoop: 2
					cel: 0
					posn: 114 45
					setMotion: MoveTo 131 45
				)
				(ego setLoop: 3 setMotion: MoveTo 132 49 self)
			)
			(4
				(ego setHeading: 0)
				(= ticks 30)
			)
			(5
				(Bset fWokeUpSheriff)
				(ego
					view: 525
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo 111 53 self
				)
			)
			(6
				(NormalEgo)
				(tumbleMusic play:)
				(ego
					view: 507
					setCycle: Forward
					yStep: 12
					xStep: 9
					illegalBits: 0
					ignoreActors:
					setLoop: 0
					setMotion: DPath 90 51 64 71 27 93 self
				)
			)
			(7
				(tumbleMusic stop:)
				(self cue:)
			)
			(8
				(tumbleMusic play:)
				(ego
					setLoop: 1
					setCel: 0
					setCycle: Forward
					setMotion: DPath 27 97 36 121 44 146 self
				)
			)
			(9
				(sheriff setLoop: 2)
				(ego
					view: 507
					setLoop: 2
					setCel: 0
					setCycle: 0
					setMotion: 0
					posn: 52 183
				)
				(egoHead
					view: 507
					setLoop: 3
					setCel: 0
					posn: 51 156
					init:
				)
				(stars
					view: 507
					setLoop: 4
					setCel: 0
					posn: 48 159
					init:
					setCycle: Forward
				)
				(tumbleMusic stop:)
				(= cycles 20)
			)
			(10
				(EgoDead 130 131 1 0 503)
			)
		)
	)
)

(instance rightScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load RES_VIEW 507)
				(LoadMany RES_SOUND 15)
				(self cue:)
			)
			(1
				(ego illegalBits: 0)
				(rightDoor setPri: 0 setCycle: EndLoop self)
			)
			(2
				(ego
					setPri: (+ (rightDoor priority?) 1)
					setMotion: MoveTo 175 40 self
				)
			)
			(3
				(= sheriffCue cueRight)
				(messager say: N_ROOM NULL NULL 14 curRoom)
				(pillow
					view: 602
					loop: 6
					cel: 0
					posn: 184 19
					init:
					setPri: 1
					setCycle: Forward
					startUpd:
				)
				(sheriff
					view: 601
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: Walk
					stopUpd:
				)
				(= seconds 3)
			)
			(4
				(pillow dispose:)
				(leftDoor setCycle: EndLoop)
				(sheriff
					setLoop: 2
					cel: 0
					posn: 115 42
					setMotion: MoveTo 131 51
				)
				(ego
					setLoop: 3
					setPri: -1
					setCycle: Reverse
					setMotion: MoveTo 175 53 self
				)
			)
			(5
				(leftDoor stopUpd:)
				(sheriff setLoop: 4 setCel: 0)
				(ego
					view: 525
					setLoop: 0
					setCel: 0
					posn: 163 53
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego
					view: 507
					setCycle: 0
					setPri: 9
					setCel: 0
					setLoop: 1
					posn: 178 25
					cel: 0
					setStep: 4 14
					setCycle: CycleTo 1 1 self
				)
			)
			(7
				(ego setPri: 10 setCycle: CycleTo 2 1 self)
			)
			(8
				(sheriff setCycle: EndLoop)
				(ego setCycle: Forward setMotion: MoveTo 175 125 self)
				(gerta
					view: 602
					setLoop: 3
					setCel: 0
					posn: 187 42
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: EndLoop
				)
			)
			(9
				(sheriff stopUpd:)
				(gerta stopUpd:)
				(rightDoor stopUpd:)
				(ego posn: 175 143 setLoop: 6 setCel: 0)
				(stars
					view: 507
					loop: 4
					cel: 0
					posn: 176 148
					init:
					setCycle: Forward
					startUpd:
				)
				(= cycles 2)
			)
			(10
				(otto
					view: 602
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: Walk
					stopUpd:
				)
				(bottomDoor
					setLoop: 7
					cel: 0
					posn: 146 132
					setPri: 9
					cycleSpeed: 3
					setCycle: CycleTo 2 1 self
				)
			)
			(11
				(miscMusic loop: 1 number: (SoundFX 15) play:)
				(bottomDoor setCel: 2 setCycle: CycleTo 4 1 self)
				(otto setLoop: 1 cel: 1 posn: 149 121)
			)
			(12
				(otto setMotion: MoveTo 161 132 self)
			)
			(13
				(otto setLoop: 5 setPri: 10 setCycle: Forward)
				(= cycles 18)
			)
			(14
				(sneakMusic stop:)
				(EgoDead 134 135 6 0 507) ;corrected death icon
			)
		)
	)
)

(instance upScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= savePri (ego priority?))
				(ego setPri: 2 setLoop: 3 setMotion: PolyPath 26 110 self)
			)
			(1
				(ego setLoop: 0 setMotion: PolyPath 155 53 self)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(ego setMotion: MoveTo 155 53 self)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance downScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: PolyPath 34 103 self)
			)
			(1
				(ego setMotion: PolyPath 46 166 self)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(ego setMotion: MoveTo 46 175 self)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(ego setPri: savePri)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toTheDesk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 89)
				(= toY 157)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (Btst fSearchedDrawer)
					(messager say: N_ROOM NULL NULL 16 self)
				else
					(ego setScript: robDesk)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toThePortrait of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 219)
				(= toY 145)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(cond 
					((not vaseOutOfWay)
						(ego setScript: vaseScript)
					)
					((not (Btst fUncoveredSafe))
						(Bset fUncoveredSafe)
						(= safeRevealed TRUE)
						(SolvePuzzle f321MovePainting 1 THIEF)
						(ego setScript: raisePainting)
					)
					(safeOpen
						(messager say: N_ROOM NULL NULL 17 self)
					)
					(else
						(ego setScript: lowerPainting)
					)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toTheSafe of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and safeOpen (not (Btst fSearchedSafe)))
					(ego get: iSilver 50)
				)
				(HandsOff)
				(= toX 219)
				(= toY 145)
				(= saveMoveSpeed (ego moveSpeed?))
				(= saveCycleSpeed (ego cycleSpeed?))
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(ego cycleSpeed: 6 moveSpeed: 6)
				(= ticks 30)
			)
			(4
				(= toX 0)
				(= toY 0)
				(cond 
					((== safeCrackSuccess 2)
						(ego setScript: bustedScript)
					)
					(
						(and
							(TrySkill PICK 0 (- lockPickBonus 20))
							(not (Btst fCrackedSafe))
						)
						(ego setScript: openSafeDoor)
					)
					((and (Btst fSearchedSafe) (Btst fCrackedSafe))
						(safeDoor setCycle: BegLoop)
						(Bclr fCrackedSafe)
						(= safeOpen FALSE)
						(onOpenSafe dispose:)
						(messager say: N_ROOM NULL C_SAFEEMPTY 2 self)
					)
					(safeOpen
						(Bset fSearchedSafe)
						(SolvePuzzle f321LootSafe 1 THIEF)
						(messager say: N_ROOM NULL C_LOOTSAFE 1 self)
					)
					((> (++ safeCrackTries) 2)
						(curRoom setScript: bustedScript)
					)
					(else
						(messager say: N_ROOM NULL C_CRACKFAIL 1 self)
					)
				)
			)
			(5
				(ego cycleSpeed: saveCycleSpeed moveSpeed: saveMoveSpeed)
				(= seconds 2)
			)
			(6
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toTheVase of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 219)
				(= toY 145)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (Btst fMovedVase)
					(Bset fStoleVase)
					(= vaseOutOfWay TRUE)
					(messager say: N_ROOM NULL NULL 18 self)
					(SolvePuzzle f321StealVase 1 THIEF)
					(theVase dispose:)
				else
					(ego setScript: vaseDown)
				)
			)
			(4
				(HandsOn)
				(if (Btst fMovedVase)
					(ego get: iVase)
				)
				(self dispose:)
			)
		)
	)
)

(instance toTheCandelabra of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 81)
				(= toY 130)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(Bset fStoleCandelabra)
				(candelabra dispose:)
				(messager say: N_ROOM NULL NULL 19 self)
				(SolvePuzzle f321StealCandelabra 1 THIEF)
			)
			(4
				(HandsOn)
				(ego get: iCandelabra)
				(self dispose:)
			)
		)
	)
)

(instance toTheBox of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 207)
				(= toY 174)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(ego illegalBits: 0 ignoreActors: 1)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (Btst fStoleMusicBox)
					(musicBox dispose:)
					(miscMusic stop:)
					(messager say: N_ROOM NULL NULL 20 self)
					(onTable init:)
					(SolvePuzzle f321StealMusicBox 1 THIEF)
				else
					(Bset fStoleMusicBox)
					(ego setScript: faceTheMusicScript)
				)
			)
			(4
				(HandsOn)
				(if (Btst fStoleMusicBox)
					(ego get: iMusicBox)
				)
				(self dispose:)
			)
		)
	)
)

(instance toTheLeftDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 125)
				(= toY 47)
				(if (< (ego y?) 55)
					(self cue:)
				else
					(ego setMotion: PolyPath 41 170 self)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self cue:)
				else
					(self setScript: upScript self)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (== hearTheDoor TRUE)
					(messager say: N_ROOM NULL NULL 21 self)
					(= hearTheDoor FALSE)
				else
					(ego setScript: leftScript)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance toTheRightDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 154)
				(= toY 47)
				(if (< (ego y?) 55)
					(self cue:)
				else
					(ego setMotion: PolyPath 41 170 self)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self cue:)
				else
					(self setScript: upScript self)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (== hearTheDoor TRUE)
					(messager say: N_ROOM NULL NULL 22 self)
					(= hearTheDoor FALSE)
				else
					(ego setScript: rightScript)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toTheBottomDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= toX 148)
				(= toY 140)
				(if (< (ego y?) 55)
					(ego setMotion: PolyPath 118 51 self)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego y?) 55)
					(self setScript: downScript self)
				else
					(self cue:)
				)
			)
			(2
				(if (> toX 0)
					(NormalEgo)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(= toX 0)
				(= toY 0)
				(if (== hearTheDoor TRUE)
					(messager say: N_ROOM NULL NULL 23 self)
					(= hearTheDoor FALSE)
				else
					(ego setScript: bottomScript)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance sneakMusic of Sound
	(properties
		number 8
		loop -1
	)
)

(instance miscMusic of Sound
	(properties
		number 13
		priority 1
	)
)

(instance tumbleMusic of Sound
	(properties
		number 83
		priority 1
	)
)
