;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Intrface)
(use castle)
(use DLetter)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm063 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	[local7 42] = [0 189 0 0 319 0 319 142 255 136 255 131 227 126 124 126 124 127 205 127 214 135 117 142 101 142 101 147 74 149 55 152 37 159 29 165 36 171 54 173 136 189]
	[local49 12] = [182 189 278 185 312 153 300 143 319 143 319 189]
	[local61 9] = [1002 116 63 4 11 29 20 28 29]
)
(instance rm063 of KQ5Room
	(properties
		picture 63
		east 62
		south 64
	)
	
	(method (init)
		(super init:)
		(= global357 270)
		(= global358 156)
		(= global355 45)
		(= global356 160)
		(cond 
			((== wizardState 5)
				(if (not (Btst 66)) (wand init:) (= local1 1))
				((ScriptID 550 7) init: setScript: wizSleepingScript)
			)
			((== wizardState 6)
				(CastleHandsOff)
				(= wizardState 8)
				(= local2 1)
				(= wizardAngle 135)
				(= global354 315)
				((ScriptID 550 7)
					view: 706
					setLoop: 0
					posn: 155 142
					setPri: 8
					init:
					setScript: wakeUpScript
				)
			)
			((not wizardTimer)
				(if (> (Random 0 100) 90)
					(Load SOUND 835)
					(Load VIEW 703)
					(= local2 1)
					(= wizardAngle 135)
					(= global354 315)
					((ScriptID 550 7)
						init:
						view: 705
						setLoop: 8
						setScript: wizWaitingScript
					)
				else
					(if (> (Random 0 100) 20) (InitCat))
					(if (== catState 1)
						(= global359 0)
					else
						(= global359 121)
					)
				)
			)
		)
		(switch prevRoomNum
			(south
				(curRoom setScript: enterSouth)
			)
			(else 
				(curRoom setScript: enterEast)
			)
		)
		(ego init:)
		(poly1 points: @local7 size: 21)
		(poly2 points: @local49 size: 6)
		(self
			setFeatures: theWindows statue table door bed robin interior
			setRegions: 550
			addObstacle: poly1 poly2
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((ego inRect: 288 138 320 150) (curRoom setScript: exitEast))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(switch temp0
					(64
						(curRoom setScript: exitSouth)
					)
				)
			)
			((ego inRect: 53 141 125 206)
				(= wizardAngle 135)
				(= global354 135)
				(switch wizardState
					(3
						(= wizardX 87)
						(= wizardY 157)
						((ScriptID 550 7) init: setScript: (ScriptID 550 12))
					)
					(6
						(= wizardState 7)
						((ScriptID 550 7) setScript: wakeUpScript)
					)
				)
			)
			((ego inRect: 206 145 260 171)
				(if (< (ego y?) 153)
					(= global354 225)
				else
					(= global354 315)
				)
				(switch wizardState
					(3
						(if (< (ego x?) 230)
							(= wizardAngle 225)
							(= global354 45)
						else
							(= wizardAngle 135)
							(= global354 315)
						)
						(= wizardX 230)
						(= wizardY 135)
						((ScriptID 550 7) init: setScript: (ScriptID 550 12))
					)
					(6
						(= wizardState 7)
						((ScriptID 550 7) setScript: wakeUpScript)
					)
				)
			)
		)
		(if
			(and
				global359
				(not (Btst 64))
				(!= local5 (GetTime 1))
			)
			(= local5 (GetTime 1))
			(if (not (-- global359)) (= wizardTimer 2))
		)
		(if (< (ego y?) 133)
			(if
				(and
					(not local0)
					(or (== wizardState 3) (== wizardState 6))
				)
				(CastleHandsOff)
				(= local0 1)
				(ego setMotion: PolyPath 267 151)
			)
			(ego setPri: 7)
		else
			(ego setPri: -1)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance glow1 of Prop
	(properties
		x 79
		y 189
		view 706
		loop 1
		priority 15
		signal $0010
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 648)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance crystal of Prop
	(properties
		x 222
		y 114
		view 706
		priority 15
		signal $0010
		detailLevel 3
	)
)

(instance table of RFeature
	(properties
		nsTop 126
		nsLeft 106
		nsBottom 139
		nsRight 131
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if local1 (SpeakAudio 649) (event claimed: 1))
				)
				(verbDo
					(if local1
						(CastleHandsOff)
						(curRoom setScript: getWand)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance statue of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 650)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bed of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if (cast contains: (ScriptID 550 7))
						(SpeakAudio 35)
					else
						(SpeakAudio 651)
					)
					(event claimed: 1)
				)
				(verbDo
					(if (cast contains: (ScriptID 550 7))
						(SpeakAudio 655)
					else
						(SpeakAudio 656)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 652)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance interior of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 653)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance enterEast of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					view: 0
					posn: 319 142
					setMotion: PolyPath 267 151 self
				)
			)
			(1
				(CastleHandsOn)
				(if local2 (wizWaitingScript cue:))
				(client setScript: 0)
			)
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(if (or (== wizardState 3) (== wizardState 6))
					(self changeState: 100)
				else
					(ego setMotion: MoveTo 319 142 self)
				)
			)
			(1 (curRoom newRoom: 62))
			(100
				(ego setMotion: MoveTo 267 151 self)
			)
			(101
				(switch wizardState
					(3
						(= wizardAngle 135)
						(= global354 225)
						(= wizardX 230)
						(= wizardY 135)
						((ScriptID 550 7) init: setScript: (ScriptID 550 12))
					)
					(6
						(= wizardState 7)
						((ScriptID 550 7) setScript: wakeUpScript)
					)
				)
			)
		)
	)
)

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego setMotion: MoveTo 151 273 self)
			)
			(1 (curRoom newRoom: 64))
		)
	)
)

(instance wand of Prop
	(properties
		x 117
		y 128
		view 708
		loop 2
		priority 9
		signal $4010
		detailLevel 3
	)
	
	(method (doit)
		(super doit:)
		(if (and (> (++ local4) 20) (not local3))
			(= local3 1)
			(= local4 0)
			(self setCycle: EndLoop)
		)
		(if (and (> local4 45) (== local3 1))
			(= local3 0)
			(= local4 0)
			(self setCycle: BegLoop)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 649)
					(event claimed: 1)
				)
				(verbDo
					(CastleHandsOff)
					(curRoom setScript: getWand)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance getWand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: PolyPath 131 141 self
				)
			)
			(1
				(CastleEgoSpeed)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 708
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2 (= cycles 5))
			(3
				(= local1 0)
				(wand dispose:)
				(CastleEgoSpeed)
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
				(Bset 66)
				(ego get: 35)
				(SolvePuzzle 3)
			)
			(4
				((ego head?) show:)
				(ego normal: 1 view: 0)
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance wizSleepingScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				0
				((ScriptID 550 7)
					view: 706
					setLoop: 0
					posn: 155 142
					setPri: 8
				)
				(= cycles 1)
			)
			(1 1 (= seconds (Random 5 20)))
			(2
				2
				((ScriptID 550 7) setCycle: EndLoop self)
			)
			(3 3 (= seconds (Random 5 20)))
			(4
				4
				((ScriptID 550 7) setCycle: BegLoop self)
				(= state 0)
			)
		)
	)
)

(instance wakeUpScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(Load SOUND 835)
				(Load VIEW 703)
				(CastleHandsOff)
				((ScriptID 550 7)
					setLoop: 1
					cycleSpeed: 0
					cel: 0
					ignoreActors: 1
					illegalBits: 0
					setPri: -1
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 550 7) setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: wizWaitingScript)
			)
		)
	)
)

(instance wizWaitingScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theMusic stop:)
				(theMusic3 number: 835 loop: 1 playBed:)
				(cond 
					((== wizardState 7) (self cue:))
					((!= wizardState 8)
						(switch prevRoomNum
							(64
								((ScriptID 550 7) posn: 101 163)
							)
							(else 
								((ScriptID 550 7) posn: 196 130 setPri: 7)
							)
						)
					)
				)
			)
			(1
				(CastleHandsOff)
				(ego setMotion: 0)
				(if (not wizardAngle)
					(if (< (ego x?) ((ScriptID 550 7) x?))
						(= wizardAngle 225)
						(= global354 45)
					else
						(= wizardAngle 135)
						(= global354 315)
					)
				)
				(proc762_1 @local61 926)
				(switch wizardAngle
					(135 (= temp0 8))
					(225 (= temp0 9))
					(45 (= temp0 10))
					(315 (= temp0 11))
				)
				((ScriptID 550 7)
					view: 705
					setLoop: temp0
					cycleSpeed: 2
					cel: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				((ScriptID 550 1)
					init:
					setCycle: Forward
					posn: (+ ((ScriptID 550 7) x?) 19) (- ((ScriptID 550 7) y?) 41)
				)
				(= seconds 2)
			)
			(3
				((ego head?) hide:)
				(switch global354
					(135 (= temp0 0))
					(225 (= temp0 1))
					(45 (= temp0 6))
					(315 (= temp0 7))
				)
				(CastleEgoSpeed)
				(ego
					view: 132
					normal: 0
					setLoop: temp0
					setCycle: EndLoop self
				)
			)
			(4
				(CastleEgoSpeed)
				(ego setLoop: (+ (ego loop?) 2) setCycle: Forward)
				(= seconds 6)
			)
			(5
				((ScriptID 550 1) hide:)
				((ScriptID 550 7) setCel: 255)
				(switch global354
					(135 (= temp0 4))
					(225 (= temp0 5))
					(45 (= temp0 10))
					(315 (= temp0 11))
				)
				(CastleEgoSpeed)
				(ego setLoop: temp0 cel: 0 setCycle: EndLoop self)
			)
			(6
				((ScriptID 550 7) cycleSpeed: 3 setCycle: BegLoop)
				(= seconds 4)
				(= inCartoon 0)
			)
			(7
				(= deathMessage 657)
				(EgoDead 73 0 -1)
			)
		)
	)
)

(instance theWindows of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 654)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance enterSouth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					view: 0
					posn: 151 273
					setMotion: PolyPath 151 183 self
				)
			)
			(1
				(if local2 (wizWaitingScript cue:) else (CastleHandsOn))
				(client setScript: 0)
			)
		)
	)
)

(instance robin of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(KQPrint 63 0)
					(event claimed: 1)
				)
			)
		)
	)
)
