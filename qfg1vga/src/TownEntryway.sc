;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include game.sh) (include "300.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm300 0
	sheriffTalker 1
)

(local
	nearExit
	local1
	yoyoSpins
	timesClimbedGate
	local4
	oldSortedFeatures
	tookAPuff
	[local7 2]
	sheriffOnScreen
	sheriffTellMainBranch = [
		STARTTELL
		C_MOUNTAIN
		-22		;C_TOWN
		-15		;C_MONSTERS
		-8		;C_BRIGANDS
		-6		;C_ADVENTURE
		-17		;C_NAME
		ENDTELL
		]
	sheriffTell1 = [
		STARTTELL
		C_BARON
		C_MAGIC
		C_INN
		C_GUILDHALL
		ENDTELL]
	sheriffTell2 = [
		STARTTELL
		-24		;C_WOLFGANG
		ENDTELL
		]
	sheriffTell3 = [
		STARTTELL
		C_GUILDHALL
		ENDTELL
		]
	sheriffTell5 = [
		STARTTELL
		-14		;C_MERCHANT
		-23		;C_TREASURE
		ENDTELL
		]
	sheriffTell4 = [
		STARTTELL
		C_INN
		ENDTELL
		]
	sheriffTell6 = [
		STARTTELL
		C_DANGER
		ENDTELL
		]
	sheriffTell7 = [
		STARTTELL
		C_BARON
		-24		;C_WOLFGANG
		ENDTELL
		]
	sheriffTell8 = [
		STARTTELL
		-18		;C_OTTO
		ENDTELL
		]
	sheriffTell9 = [
		STARTTELL
		C_PRISONERS
		ENDTELL
		]
	[sheriffTellTree 13]
	sheriffTellKeys = [
		STARTTELL
		-22		;C_TOWN
		-15		;C_MONSTERS
		-24		;C_WOLFGANG
		-8		;C_BRIGANDS
		-14		;C_MERCHANT
		-23		;C_TREASURE
		-6		;C_ADVENTURE
		-24		;C_WOLFGANG
		-17		;C_NAME
		-18		;C_OTTO
		ENDTELL
		]
)
(procedure (LookAtTown)
	(if (< timeODay TIME_SUNSET)
		(messager say: N_ROOM NULL C_DAY)
	else
		(messager say: N_ROOM NULL C_NIGHT)
	)
)

(instance rm300 of Room
	(properties
		noun N_ROOM
		picture 300
		style DISSOLVE
	)
	
	(method (init)
		(= [sheriffTellTree 0] @sheriffTellMainBranch)
		(= [sheriffTellTree 1] @sheriffTell1)
		(= [sheriffTellTree 2] @sheriffTell2)
		(= [sheriffTellTree 3] @sheriffTell3)
		(= [sheriffTellTree 4] @sheriffTell4)
		(= [sheriffTellTree 5] @sheriffTell5)
		(= [sheriffTellTree 6] @sheriffTell6)
		(= [sheriffTellTree 7] @sheriffTell7)
		(= [sheriffTellTree 8] @sheriffTell3)
		(= [sheriffTellTree 9] @sheriffTell8)
		(= [sheriffTellTree 10] @sheriffTell9)
		(= [sheriffTellTree 11] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						282 189
						316 148
						316 93
						288 93
						282 107
						265 105
						251 110
						258 120
						225 143
						185 149
						164 145
						147 151
						123 152
						106 149
						89 149
						66 144
						52 133
						0 133
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 162
						35 177
						200 177
						200 189
						0 189
					yourself:
				)
		)
		(LoadMany VIEW 300 515 606 517)
		(ego
			illegalBits: (if (< timeODay TIME_SUNSET) cYELLOW else cWHITE)
			ignoreControl: cWHITE
			init:
		)
		(super init: &rest)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(self
			setFeatures:
				onBarberNote
				onInnSign
				tubOSuds
				onJailSign
				sheriffsDoor
				statue1
				statue2
				innWindow
				jailCell
				jailWindow
				onScissors
		)
		;UPGRADE
;;;		(onBarberNote init:)
;;;		(onInnSign init:)
;;;		(tubOSuds init:)
;;;		(onJailSign init:)
;;;		(sheriffsDoor init:)
;;;		(statue1 init:)
;;;		(innWindow init:)
;;;		(jailCell init:)
;;;		(jailWindow init:)
;;;		(onScissors init:)
		
		(theMusic fade:)
		(self setRegions: STREET TOWN)
		(if (or (not Night) (and (== prevRoomNum 65) (Btst fTownGateOpen)))
			(openedGate init: stopUpd:)
		else
			(closedGate init: stopUpd:)
		)
		(= perspective 70)
		(= yoyoSpins 1)
		(if (not (Btst fBeenIn300))
			(= Day 0)
			(FixTime 11)
			(Bset fInMainGame)
		)
		(theIconBar enable:)
		(NormalEgo)
		(if (< timeODay TIME_SUNSET)
			(sheriffTeller init: sheriff @sheriffTellMainBranch @sheriffTellTree @sheriffTellKeys)
			(sheriff init: actions: sheriffTeller)
			(yoyo init:)
			(otto init:)
			(= sheriffOnScreen TRUE)
		else
			(= sheriffOnScreen FALSE)
		)
		(innDoor cycleSpeed: 6 ignoreActors: init: stopUpd:)
		(switch prevRoomNum
			(301
				(innDoor setScript: closeInnDoor)
			)
			(310
				(curRoom setScript: enterFrom310)
			)
			(320
				(ego posn: 305 113 setMotion: MoveTo 305 125)
			)
			(999
				(ego
					init:
					view: 515
					setLoop: 3
					setCel: 5
					setScript: egoWakes
				)
			)
			(73
				(ego
					init:
					setCel: 0
					posn: 26 163
					setMotion: MoveTo 55 154
				)
			)
			(else 
				(HandsOff)
				(ego posn: 256 235)
				(curRoom setScript: sEnter)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not nearExit)
				(> (ego x?) 0)
				(< (ego y?) 189)
				(> (ego y?) 124)
			)
			(= nearExit TRUE)
		)
		(cond 
			(script)
			((and (< (ego x?) 7) nearExit)
				(self style: SCROLLRIGHT)
				(curRoom setScript: enter310)
			)
			((and nearExit (== (ego onControl: origin) cLGREY))
				(= nearExit FALSE)
				(curRoom setScript: enter320)
			)
			((== (ego edgeHit?) SOUTH)
				(cond 
					((IsObject openedGate)
						(HandsOff)
						(curRoom setScript: sExitTown)
					)
					((== (curRoom script?) climbOut)
						(climbOut changeState: 2)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn300)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
)

(instance openedGate of View
	(properties
		x 127
		y 189
		noun 10
		view 300
		loop 1
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance closedGate of View
	(properties
		x 242
		y 189
		noun N_CLOSEDGATE
		view 300
		loop 1
		priority 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((not [egoStats CLIMB])
						(messager say: N_CLOSEDGATE V_DO NULL 1)
					)
					((<= (Random 3 10) (++ timesClimbedGate))
						(messager say: N_CLOSEDGATE V_DO NULL 2)
						(= timesClimbedGate 10)
					)
					((TrySkill CLIMB 35 0)
						(curRoom setScript: climbOut)
					)
					(else
						(messager say: N_CLOSEDGATE V_DO NULL 3)
					)
				)
			)
			(V_THIEFKIT
				(messager say: N_CLOSEDGATE V_LOCKPICK)
			)
			(V_LOCKPICK
				(messager say: N_CLOSEDGATE V_LOCKPICK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance sheriff of Actor
	(properties
		x 159
		y 120
		noun 14
		view 300
		loop 3
		priority 1
		signal fixPriOn
		illegalBits $0000
	)
	
	(method (init)
		(if (== howFast slow)
			(smoke addToPic:)
			(puff addToPic:)
		else
			(= tookAPuff FALSE)
			(smoke
				init:
				cycleSpeed: 9
				setCycle: Forward
				ignoreActors:
				stopUpd:
			)
			(puff init: ignoreActors: hide:)
			(self setScript: sheriffScript)
		)
		(super init:)
	)
)

(instance sheriffTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Face ego sheriff)
				(messager say: N_SHERIFF V_LOOK)
			)
			(V_DO
				(messager say: N_SHERIFF V_DO)
			)
			(V_TALK
				(SolvePuzzle f300TalkToSheriff 1)
				(super doVerb: theVerb)
			)
			(V_THIEFKIT
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_LOCKPICK
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_THIEFLICENSE
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_MUSICBOX
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_CANDLESTICKS
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_VASE
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_CANDELABRA
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(V_PEARLS
				(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance otto of Actor
	(properties
		x 211
		y 105
		noun N_OTTO
		view 606
		loop 1
		cycleSpeed 5
	)
	
	(method (init)
		(self setScript: yoyoLow)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				;(messager say: N_SHERIFF V_DO)
				(messager say: N_YOYO V_DO) ;Macintosh change
			)
			(V_TALK
				;restored message
				(messager say: N_OTTO V_ALTTALK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance yoyo of Actor
	(properties
		x 187
		y 103
		noun N_YOYO
		view 606
		loop 3
		priority 9
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FETCH
				(messager say: N_YOYO V_ROCK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance sheriffsDoor of Feature
	(properties
		x 169
		y 99
		z 29
		noun N_SHERIFFDOOR
		nsTop 48
		nsLeft 154
		nsBottom 93
		nsRight 184
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (< timeODay TIME_SUNSET)
					(messager say: N_SHERIFFDOOR V_DO C_DAY)
				else
					(messager say: N_SHERIFFDOOR V_DO C_NIGHT)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance innWindow of Feature
	(properties
		x 61
		y 75
		noun N_INNWINDOW
		nsTop 62
		nsLeft 48
		nsBottom 89
		nsRight 74
		sightAngle 40
	)
)

(instance jailCell of Feature
	(properties
		x 92
		y 25
		noun N_JAILCELL
		nsTop 8
		nsLeft 83
		nsBottom 43
		nsRight 101
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Face ego self)
				(messager say: N_JAILCELL V_LOOK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance jailWindow of Feature
	(properties
		x 208
		y 61
		noun N_JAILWINDOW
		nsTop 45
		nsLeft 196
		nsBottom 77
		nsRight 220
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Face ego self)
				(messager say: N_JAILWINDOW V_LOOK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance statue1 of Feature
	(properties
		x 129
		y 143
		z 47
		noun N_STATUE1
		nsTop 79
		nsLeft 118
		nsBottom 114
		nsRight 141
		sightAngle 40
	)
)

(instance statue2 of Feature
	(properties
		x 238
		y 121
		z 38
		noun N_STATUE2
		nsTop 72
		nsLeft 229
		nsBottom 94
		nsRight 247
		sightAngle 40
	)
)

(instance onBarberNote of Feature
	(properties
		x 255
		y 70
		noun N_BARBERNOTE
		nsTop 45
		nsLeft 240
		nsBottom 95
		nsRight 270
		approachX 269
		approachY 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Face ego self)
				(messager say: N_BARBERNOTE V_LOOK C_DAY)
				(if (> timeODay TIME_SUNSET)
					(messager say: N_BARBERNOTE V_LOOK C_NIGHT)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance onJailSign of Feature
	(properties
		x 207
		y 15
		noun 8
		nsTop N_JAILSIGN
		nsLeft 196
		nsBottom 26
		nsRight 218
	)
)

(instance onScissors of Feature
	(properties
		x 261
		y 26
		noun N_SCISSORS
		nsTop 17
		nsLeft 248
		nsBottom 34
		nsRight 275
	)
)

(instance onInnSign of Feature
	(properties
		x 18
		y 32
		noun N_INNSIGN
		nsTop 16
		nsLeft 1
		nsBottom 48
		nsRight 36
	)
)

(instance tubOSuds of Feature
	(properties
		x 102
		y 133
		noun N_TUBOSUDS
		nsTop 122
		nsLeft 83
		nsBottom 143
		nsRight 121
	)
)

(instance doorSlam of Sound
	(properties
		number 84
	)
)

(instance doorSound of Sound
	(properties
		number 89
	)
)

(instance yoyoSound of Sound
	(properties
		number 119
		loop -1
	)
)

(instance innDoor of Prop
	(properties
		x 36
		y 125
		noun N_INNDOOR
		approachX 36
		approachY 135
		view 300
		priority 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Face ego self)
				(messager say: N_INNDOOR V_LOOK C_DAY)
			)
			(V_DO
				(curRoom setScript: enter301)
			)
			(V_LOCKPICK
				(if (== sheriffOnScreen TRUE)
					(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
				else
					(curRoom setScript: enter301)
				)
			)
			(V_THIEFKIT
				(if (== sheriffOnScreen TRUE)
					(EgoDead C_DIE_BUSTED_STUPID C_DIE_BUSTED_STUPID_TITLE)
				else
					(curRoom setScript: enter301)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 170
		y 96
		view 300
		loop 5
		priority 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
)

(instance puff of Prop
	(properties
		x 158
		y 85
		view 300
		loop 7
		priority 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance egoWakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(messager say: N_ROOM NULL C_SLEPTINSTREET 1 self)
				((inventory at: iGold) amount: 0)
				((inventory at: iSilver) amount: 0)
				(HandsOn)
			)
			(2
				(messager say: N_ROOM NULL C_SLEPTINSTREET 2 self)
			)
		)
	)
)

(instance sEnter of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and Night (not (Btst fTownGateOpen)))
					(curRoom setScript: climbIn)
				else
					(self cue:)
				)
			)
			(1
				(SolvePuzzle f300EnterTown 1)
				(ego setMotion: MoveTo 237 181 self)
			)
			(2
				(ego setHeading: 45)
				(= ticks 90)
			)
			(3
				(ego setHeading: 270)
				(= ticks 90)
			)
			(4
				(ego setHeading: 315)
				(= ticks 30)
			)
			(5
				(if (== prevRoomNum 65)
					(HandsOn)
					(self dispose:)
				else
					(LookAtTown)
					(= ticks 5)
				)
			)
			(6
				(ego setMotion: MoveTo 187 167 self)
			)
			(7
				(ego setHeading: 0 self)
			)
			(8
				(messager say: N_ROOM NULL C_FIRSTENTRY 0 self)
			)
			(9
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitTown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 252 240 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 65)
			)
		)
	)
)

(instance closeInnDoor of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(doorSound play:)
				(innDoor setCycle: EndLoop self)
			)
			(1
				(ego loop: 2 posn: 22 127 setMotion: MoveTo 25 135 self)
			)
			(2
				(innDoor setCycle: BegLoop self)
			)
			(3
				(doorSlam play:)
				(HandsOn)
				(innDoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance enter301 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 22 127 self)
			)
			(1
				(doorSound play:)
				(innDoor setCycle: EndLoop self)
			)
			(2
				(doorSound stop:)
				(ego setMotion: MoveTo 22 121 self)
			)
			(3
				(curRoom newRoom: 301)
			)
		)
	)
)

(instance sheriffScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not tookAPuff)
					(smoke show: startUpd:)
					(= tookAPuff TRUE)
				)
				(= seconds (Random 3 12))
			)
			(1
				(smoke hide: stopUpd:)
				(sheriff
					startUpd:
					cycleSpeed: 6
					cel: 1
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				(sheriff setCycle: CycleTo 9 1)
				(puff show: startUpd: cycleSpeed: 9 setCycle: EndLoop self)
			)
			(3
				(sheriff setCycle: EndLoop self)
			)
			(4
				(sheriff setCel: 0)
				(smoke show: setCycle: Forward startUpd:)
				(puff hide: stopUpd:)
				(self changeState: 0)
			)
		)
	)
)

(instance yoyoLow of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(yoyoSound number: 119 loop: -1 init: play:)
				(self cue:)
			)
			(1
				(++ yoyoSpins)
				(otto setLoop: 0 cycleSpeed: 12 setCycle: Forward)
				(yoyo
					setLoop: 4
					posn: 187 103
					cycleSpeed: 6
					setCycle: Forward
				)
				(= ticks 90)
			)
			(2
				(otto setCycle: BegLoop self)
			)
			(3
				(if (== (mod yoyoSpins 5) 0)
					(client setScript: yoyoMiddle)
				else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance yoyoMiddle of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (/ yoyoSpins 5))
				(++ yoyoSpins)
				(otto loop: 1 cel: 0 cycleSpeed: 8 setCycle: Forward)
				(yoyo setLoop: 4 posn: 191 82 setStep: 1 1 cycleSpeed: 4)
				(= ticks 90)
			)
			(1 (otto setCycle: CycleTo 1 1 self))
			(2
				(if register
					(-- register)
					(self cue:)
				else
					(self changeState: 5)
				)
			)
			(3
				(yoyoSound pause: TRUE)
				(yoyo setLoop: 3 setMotion: MoveTo 191 70 self)
			)
			(4
				(yoyo setMotion: MoveTo 191 82 self)
			)
			(5
				(yoyoSound pause: FALSE)
				(if (<= register 0)
					(self cue:)
				else
					(self changeState: 2)
				)
			)
			(6
				(yoyo setLoop: 4)
				(otto setCycle: Forward)
				(= ticks 120)
			)
			(7
				(if
					(or
						(< yoyoSpins 16)
						(== (curRoom script?) sExitTown)
						(== (curRoom script?) enter310)
						(== (curRoom script?) enter301)
						(== (curRoom script?) enter320)
					)
					(client setScript: yoyoLow)
				else
					(client setScript: yoyoHigh)
				)
			)
		)
	)
)

(instance yoyoHigh of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= yoyoSpins 1)
				(yoyo
					posn: 191 82
					setLoop: 4
					cycleSpeed: 2
					setStep: 12 12
					setCycle: Forward
				)
				(otto setLoop: 1 cycleSpeed: 2 setCycle: Forward)
				(= ticks 120)
			)
			(1
				(otto setLoop: 2 cel: 0)
				(yoyoSound number: 124 loop: 1 play:)
				(yoyo setLoop: 3 setMotion: MoveTo 196 88 self)
			)
			(2
				(otto cel: 1)
				(yoyo setStep: 8 8 posn: 196 77)
				(= ticks 1)
			)
			(3
				(otto setCycle: CycleTo 3 1 self)
			)
			(4
				(yoyo
					setPri: 15
					cycleSpeed: 1
					setMotion: MoveTo 196 20 self
				)
			)
			(5
				(if
					(or
						(== (curRoom script?) sExitTown)
						(== (curRoom script?) enter310)
						(== (curRoom script?) enter301)
						(== (curRoom script?) enter320)
					)
					(self changeState: 10)
				else
					(HandsOff)
					(ego setMotion: 0)
					(yoyo setStep: 36 24)
					(= ticks 20)
				)
			)
			(6
				(otto setLoop: 1 setCel: 1)
				(if (<= (ego x?) 190)
					(yoyo setMotion: MoveTo (ego x?) (- (ego y?) 47) self)
				else
					(yoyo
						setMotion: MoveTo (- (ego x?) 6) (- (ego y?) 47) self
					)
				)
			)
			(7
				(yoyoSound number: 125 play:)
				(self cue:)
			)
			(8
				(HandsOn)
				(otto cycleSpeed: 6 setLoop: 2 setCel: 3)
				(if (<= (ego x?) 190)
					(yoyo setMotion: MoveTo (ego x?) 20 self)
				else
					(yoyo setMotion: MoveTo (- (ego x?) 6) 20 self)
				)
			)
			(9
				(yoyo setMotion: MoveTo 197 20 self)
			)
			(10
				(yoyo
					setStep: 8 8
					setPri: 9
					setMotion: MoveTo 197 88 self
				)
			)
			(11
				(otto setCycle: EndLoop)
				(yoyo
					setStep: 1 1
					setPri: 9
					setMotion: MoveTo 197 91 self
				)
			)
			(12
				(= ticks 30)
			)
			(13
				(client setScript: yoyoLow)
			)
		)
	)
)

(instance climbOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(ego setPri: 12 setMotion: PolyPath 300 165 self)
			)
			(1
				1
				(if egoGait
					(ego ignoreActors: TRUE setMotion: MoveTo 315 189 self)
				else
					(ego ignoreActors: TRUE setMotion: MoveTo 300 202 self)
				)
			)
			(2
				2
				(if egoGait
					(self cue:)
				else
					(ego
						posn: (ego x?) (+ (ego y?) 3)
						setMotion: MoveTo 300 208 self
					)
				)
			)
			(3 3 (ego hide:) (= ticks 60))
			(4
				(ego
					view: 517
					setLoop: 8
					y: 0
					setPri: 15
					show:
					posn: 261 179
					setCycle: EndLoop self
				)
			)
			(5 5 (= ticks 20))
			(6
				(ego
					setLoop: 1
					cel: 6
					priority: 15
					ignoreActors: 0
					setCycle: BegLoop self
				)
			)
			(7 7 (= ticks 5))
			(8
				8
				(HandsOn)
				(curRoom newRoom: 65)
			)
		)
	)
)

(instance climbIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 517
					setLoop: 1
					cel: 0
					posn: 261 179
					setPri: 15
					show:
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 30))
			(2
				(ego setLoop: 8 cel: 8 priority: 15 setCycle: BegLoop self)
			)
			(3
				(ego hide:)
				(self cue:)
			)
			(4
				(ego
					view: 0
					loop: 3
					cel: 0
					posn: 300 225
					priority: 12
					show:
					setCycle: Walk
					setMotion: MoveTo 300 165 self
				)
			)
			(5
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance enter310 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -30 142 self)
			)
			(1
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance enterFrom310 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: -30 142 setMotion: MoveTo 32 148 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enter320 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (not (Btst fBeenIn300)) (< timeODay TIME_SUNSET))
					(messager say: N_ROOM NULL C_SHERIFFWARNS 1 self)
				else
					(self cue:)
				)
			)
			(1
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance sheriffTalker of Talker
	(properties
		x 15
		y 8
		view 1300
		talkWidth 260
		textX 13
		textY 110
	)
	
	(method (init)
		(= nightPalette 2300)
		(PalVary PALVARYTARGET 2300)
		(AssertPalette 1300)
		(= font userFont)
		(super init: sheriffBust sheriffEyes sheriffMouth &rest)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (show)
		(super show:)
	)
)

(instance sheriffBust of Prop
	(properties
		view 1300
	)
)

(instance sheriffMouth of Prop
	(properties
		nsTop 42
		nsLeft 43
		view 1300
		loop 1
	)
)

(instance sheriffEyes of Prop
	(properties
		nsTop 35
		nsLeft 45
		view 1300
		loop 2
	)
)
