;;; Sierra Script 1.0 - (do not remove this comment)
(script# 334)
(include game.sh) (include "334.shm")
(use Main)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm334 0
	slinkTalker 1
)

(local
	thievesOnScreen
	thievesSatisfied
	local2
	climbAttempts
	thiefClicked
	local5
)
(instance rm334 of Room
	(properties
		noun N_ROOM
		picture 333
		style HSHUTTER
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						257 189
						213 151
						177 145
						170 137
						165 111
						143 112
						138 119
						143 133
						148 149
						121 179
						108 184
						85 189
					yourself:
				)
		)
		(features
			add:
				onTheWalls
				onBarrels
				onBarrels2
				onHotDogStand
				onBricks
				marksOnWall2
				marksOnWall
			eachElementDo: #init
		)
		(coin ignoreActors: TRUE setLoop: 6 setPri: 6 init:)
		(LoadMany VIEW 333 503)
		(super init:)
		(switch prevRoomNum
			(53
				(ego loop: 2 posn: 160 130 init: setScript: moveEgoIn)
			)
			(else 
				(if (not Night)
					(FixTime 20)
				)
				(ego init: posn: 162 247 setLoop: 3)
				(self setScript: sEnter)
			)
		)
		(self setRegions: STREET TOWN)
		;(self setRegions: STREET) ;UPGRADE: Don't make this area part of the Town region, since Erana's aura doesn't cover it.

	)
	
	(method (doit)
		(cond 
			(script)
			(
				(and
					(not thievesOnScreen)
					(not thievesOnScreen)
					(< 140 (ego y?))
					(< (ego y?) 143)
				)
				(self setScript: ambushScript)
			)
			(
				(and
					thievesOnScreen
					(not thievesSatisfied)
					(or (> (ego y?) 143) (< (ego y?) 140))
				)
				(curRoom setScript: stabTheBum)
			)
			((== (ego edgeHit?) SOUTH) (self setScript: sExitRoom))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn334)
		(DisposeScript EXTRA)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM NULL C_FIRSTENTRY)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_TOUCHROOM)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (not thievesSatisfied)
			(curRoom setScript: stabTheBum)
		)
	)
	
	(method (newRoom n)
		(roomTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance onTheWalls of Feature
	(properties
		x 75
		y 80
		onMeCheck ISNOTHIDDEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_ROOM V_LOOK C_TOUCHROOM)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBarrels of Feature
	(properties
		x 106
		y 148
		noun N_BARREL1
		nsTop 133
		nsLeft 90
		nsBottom 164
		nsRight 122
		sightAngle 40
		onMeCheck NEARCHECK
	)
)

(instance onBarrels2 of Feature
	(properties
		x 198
		y 120
		noun N_BARREL2
		nsTop 104
		nsLeft 184
		nsBottom 136
		nsRight 212
		sightAngle 40
		onMeCheck NEARCHECK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BARREL2 V_LOOK)
			)
			(V_DO
				(messager say: N_BARREL2 V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onHotDogStand of Feature
	(properties
		x 114
		y 128
		noun N_HOTDOGSTAND
		nsTop 104
		nsLeft 105
		nsBottom 153
		nsRight 124
		sightAngle 40
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_HOTDOGSTAND V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBricks of Feature
	(properties
		x 158
		y 74
		noun N_BRICKS
		nsTop 42
		nsLeft 129
		nsBottom 104
		nsRight 190
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BRICKS V_LOOK)
			)
			(V_DO
				(cond 
					(
						(and
							thievesOnScreen
							(not thievesSatisfied)
							(or (> (ego y?) 143) (< (ego y?) 140))
						)
						(messager say: N_BRICKS V_DO)
						(HandsOff)
						(curRoom setScript: killTheBum)
					)
					((TrySkill CLIMB 35 0)
						(ego setScript: toTheCentaur)
					)
					((not [egoStats CLIMB])
						(messager say: N_BRICKS V_DO C_NOCLIMB)
						(++ climbAttempts)
					)
					((and (< 3 climbAttempts) (< climbAttempts 10))
						(messager say: N_BRICKS V_DO C_NOMORECLIMBING)
						(= climbAttempts 10)
					)
					(else
						(messager say: N_BRICKS V_DO C_CLIMBFAIL)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance marksOnWall of Feature
	(properties
		x 120
		y 103
		noun N_MARKS
		nsTop 100
		nsLeft 112
		nsBottom 107
		nsRight 128
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (<= (ego y?) 150)
					(messager say: N_MARKS V_LOOK C_NEARCOIN)
				else
					(messager say: N_MARKS V_LOOK C_TOOFAR)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance marksOnWall2 of Feature
	(properties
		x 203
		y 91
		nsTop 80
		nsLeft 194
		nsBottom 102
		nsRight 212
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(marksOnWall doVerb: theVerb &rest)
	)
)

(instance slink of Actor
	(properties
		x 176
		y 135
		noun N_SLINK
		view 333
		loop 3
		illegalBits 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== thiefClicked sneak)
					(messager say: N_ROOM V_LOOK C_CLICKSNEAK)
				else
					(messager say: N_ROOM V_LOOK C_CLICKSLINK)
				)
			)
			(V_DO
				(cond 
					(thievesSatisfied
						(messager say: 7 V_DO C_THIEVESIGNORE)
					)
					((Btst fLearnedThiefPassword)
						(messager say: 7 V_DO C_THIEVESALREADYKNOW)
						(= thievesSatisfied TRUE)
					)
					((not (if [egoStats STEALTH] else [egoStats PICK]))
						(messager say: 7 V_DO C_NOTATHIEF)
					)
					(else
						(ego setScript: messageTwo)
					)
				)
			)
			(V_LOCKPICK
				(cond 
					(thievesSatisfied
						(messager say: N_ROOM V_DO C_THIEVESIGNORE)
					)
					((Btst fLearnedThiefPassword)
						(messager say: N_ROOM V_DO C_THIEVESALREADYKNOW)
						(= thievesSatisfied TRUE)
					)
					((not (if [egoStats STEALTH] else [egoStats PICK]))
						(messager say: N_ROOM V_DO C_NOTATHIEF)
					)
					(else
						(ego setScript: messageTwo)
					)
				)
			)
			(V_THIEFKIT
				(cond 
					(thievesSatisfied
						(messager say: N_ROOM V_DO C_THIEVESIGNORE)
					)
					((Btst fLearnedThiefPassword)
						(messager say: N_ROOM V_DO C_THIEVESALREADYKNOW)
						(= thievesSatisfied TRUE)
					)
					((not (if [egoStats STEALTH] else [egoStats PICK]))
						(messager say: N_ROOM V_DO C_NOTATHIEF)
					)
					(else
						(ego setScript: messageTwo)
					)
				)
			)
			(V_TALK
				(cond 
					(thievesSatisfied
						(messager say: N_ROOM V_DO C_THIEVESIGNORE)
					)
					((Btst fLearnedThiefPassword)
						(messager say: N_ROOM V_DO C_LEARNPASSWORD 2)
					)
					(else
						(messager say: N_SLINK V_TALK)
					)
				)
			)
			(V_MONEY
				(cond 
					(thievesSatisfied
						(messager say: N_ROOM V_DO C_THIEVESIGNORE)
					)
					((Btst fLearnedThiefPassword)
						(messager say: N_ROOM V_DO C_THIEVESALREADYKNOW)
						(= thievesSatisfied TRUE)
					)
					(
						(and
							(< ((inventory at: iSilver) amount?) 5)
							(not (ego has: iGold))
						)
						(messager say: N_SLINK V_MONEY C_NOFUNDS)
						(HandsOff)
						(curRoom setScript: killTheBum)
					)
					(else
						(= thievesSatisfied TRUE)
						(messager say: N_SLINK V_MONEY C_GIVEMONEY)
						(SolvePuzzle f334Robbed -10 THIEF)
						(roomTimer setReal: curRoom 10)
						(ego setScript: giveMoney)
					)
				)
			)
			(V_DAGGER
				(messager say: N_SLINK V_DAGGER)
				(HandsOff)
				(curRoom setScript: killTheBum)
			)
			(V_SWORD
				(messager say: 8 16)
				(HandsOff)
				(curRoom setScript: killTheBum)
			)
			(V_THIEFLICENSE
				(if thievesSatisfied
					(messager say: N_ROOM V_DO C_THIEVESIGNORE)
				else
					(messager say: N_ROOM V_DO C_LEARNPASSWORD 1)
					(= thievesSatisfied TRUE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return 1)
	)
)

(instance sneak of Actor
	(properties
		x 134
		y 162
		noun N_SLINK
		view 333
		loop 4
		illegalBits 0
	)
	
	(method (doVerb theVerb)
		(= thiefClicked sneak)
		(slink doVerb: theVerb &rest)
	)
)

(instance knife of Actor
	(properties
		x 144
		y 115
		view 333
		loop 5
		priority 10
		illegalBits 0
	)
)

(instance knife2 of Actor
	(properties
		x 144
		y 115
		view 333
		loop 5
		priority 10
		illegalBits 0
	)
)

(instance coin of Extra
	(properties
		x 158
		y 111
		noun 4
		view 333
		loop 6
		cycleType 1
		pauseCel 2
		minPause 40
		maxPause 80
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (<= (ego y?) 150)
					(messager say: N_COIN V_LOOK C_NEARCOIN)
				else
					(messager say: N_COIN V_LOOK)
				)
			)
			(V_DO
				(if (not thievesSatisfied)
					(ego setMotion: PolyPath 158 117)
				else
					(messager say: N_COIN V_LOOK C_NEARCOIN)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stabTheBum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= thievesSatisfied TRUE)
				(messager say: N_SLINK NULL C_DIDNTPAY 1 self)
			)
			(1
				(ego setMotion: 0 setCycle: 0 setScript: 0)
				(sneak ignoreActors: TRUE setPri: 10 setCel: 3 init:)
				(self cue:)
			)
			(2
				(curRoom setScript: killTheBum)
			)
		)
	)
)

(instance killTheBum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load SOUND 29)
				(= seconds 2)
			)
			(1
				(sneak setCycle: CycleTo 8 1)
				(knife2
					ignoreActors: 1
					setLoop: 5
					setCel: 0
					setPri: 10
					init:
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Forward
					setMotion: MoveTo (ego x?) (- (ego y?) 23) self
				)
				(knifeSound number: 31 play:)
			)
			(2
				(knifeSound number: 29 play:)
				(knife2 setCycle: 0)
				(slink ignoreActors: 0 stopUpd:)
				(= cycles 3)
			)
			(3
				(knife2 hide:)
				(ego
					view: 503
					setLoop: 0
					cel: 0
					setPri: (- (sneak priority?) 1)
					setCycle: EndLoop self
				)
			)
			(4
				(EgoDead C_DIE_ALLEY C_DIE_ALLEY_TITLE 0 0 800)
			)
		)
	)
)

(instance moveEgoIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local5 1)
				(ego setMotion: MoveTo 148 142 self)
			)
			(1
				(ego setScript: 0)
				(curRoom setScript: ambushScript)
			)
		)
	)
)

(instance toTheCentaur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					signal: (| (ego signal?) $4000)
					illegalBits: 0
					setCycle: Walk
					setMotion: PolyPath 144 113 self
				)
			)
			(1
				(ego
					view: 517
					setLoop: 0
					setCel: 0
					posn: 146 76
					setCycle: Walk
					setMotion: MoveTo 142 62 self
				)
			)
			(2
				(ego
					setLoop: 1
					setCel: 0
					posn: 144 44
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance messageTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM V_DO C_LEARNPASSWORD 1 self)
				(SolvePuzzle 642 3 2)
				(Bset 123)
				(= thievesSatisfied 1)
			)
			(1
				(if (not (ego has: 9))
					(messager say: N_ROOM V_DO C_LEARNPASSWORD 2 self)
				else
					(self cue:)
				)
			)
			(2
				(if (not (ego has: 9))
					(messager say: N_ROOM V_DO C_LEARNPASSWORD 3 self)
				else
					(self cue:)
				)
			)
			(3
				(HandsOn)
				(self dispose:)
				(roomTimer setReal: curRoom 10)
			)
		)
	)
)

(instance slinkTalker of Talker
	(properties
		x 10
		y 10
		view 1333
		talkWidth 260
		textX 15
		textY 112
	)
	
	(method (init)
		(= nightPalette 2333)
		(PalVary PALVARYTARGET 2333)
		(kernel_128 1333)
		(= font userFont)
		(super init: slinkBust slinkEye slinkMouth &rest)
	)
)

(instance slinkBust of Prop
	(properties
		view 1333
	)
)

(instance slinkMouth of Prop
	(properties
		nsTop 44
		nsLeft 43
		view 1333
		loop 1
	)
)

(instance slinkEye of Prop
	(properties
		nsTop 22
		nsLeft 44
		view 1333
		loop 2
	)
)

(instance knifeSound of Sound)

(instance leMusic of Sound
	(properties
		flags $ffff
		number 73
		priority 3
		loop -1
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 162 185 self)
			)
			(2
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(= ticks 30)
			)
			(3
				(if (not (Btst fBeenIn334))
					(Bset fBeenIn334)
					(messager say: N_ROOM NULL C_FIRSTENTRY 0 self)
				else
					(= seconds 1)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 2 setMotion: MoveTo (ego x?) 247 self)
			)
			(1
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance ambushScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(leMusic play:)
				(Load SOUND 30)
				(Load SOUND 31)
				(= thievesOnScreen TRUE)
				(ego setMotion: PolyPath 148 142)
				(slink ignoreActors: 1 setPri: 7 init: setCycle: EndLoop self)
			)
			(1
				(Face ego slink)
				(sneak
					ignoreActors: 1
					setPri: 10
					init:
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(sneak setCycle: CycleTo 8 1)
				(knife
					ignoreActors: 1
					setLoop: 5
					setPri: 10
					init:
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 194 89 self
				)
				(knifeSound number: 31 loop: 1 play:)
			)
			(3
				(knifeSound number: 30 play:)
				(knife setCel: 2 addToPic:)
				(slink ignoreActors: 0 stopUpd:)
				(= cycles 3)
			)
			(4
				(sneak setCel: 3 ignoreActors: 0 stopUpd:)
				(= cycles 5)
			)
			(5
				(cond 
					((not (Btst fAmbushedAlley))
						(Bset fAmbushedAlley)
						(if (== prevRoomNum 53)
							(messager say: N_SLINK NULL C_AMBUSH53)
						else
							(messager say: N_SLINK NULL C_AMBUSH)
						)
						(HandsOn)
						(self dispose:)
					)
					((not (Btst fLearnedThiefPassword))
						(messager say: N_SLINK NULL C_HELLOAGAIN)
						(HandsOn)
						(self dispose:)
					)
					((not (ego has: iThiefLicense))
						(messager say: N_SLINK NULL C_NOLICENSE)
						(curRoom setScript: killTheBum)
						(self dispose:)
					)
					((Btst fSlinkWarned)
						(messager say: N_SLINK NULL C_THIRDTIME)
						(= seconds 2)
					)
					(else
						(messager say: N_SLINK NULL C_SHOWLICENSE)
						(= ticks 30)
					)
				)
				(roomTimer setReal: curRoom 20)
			)
			(6
				(if (Btst fSlinkWarned)
					(messager say: N_SLINK NULL C_IGNOREDWARNING)
					(curRoom setScript: killTheBum)
					(self dispose:)
				else
					(messager say: N_SLINK NULL C_SLINKWARNS)
					(Bset fSlinkWarned)
					(self dispose:)
					(HandsOn)
					(= thievesSatisfied TRUE)
				)
			)
		)
	)
)

(instance giveMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 30)
			)
			(1
				((inventory at: iGold) amount: 0)
				((inventory at: iSilver) amount: 1)
				(ego use: iSilver 1)
				(self cue:)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance roomTimer of Timer)
