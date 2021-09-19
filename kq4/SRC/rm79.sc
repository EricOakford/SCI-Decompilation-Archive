;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room79 0
)
(synonyms
	(goon goon goon man goon goon animal goon)
)

(define cFALL $0f8e)

(local
	h1
	h2
	local2
)
(instance henchTheme of Sound
	(properties
		number 29
		loop -1
	)
)

(instance sFalling of Sound
	(properties
		number 51
		priority 3
	)
)

(instance Room79 of Room
	(properties
		picture 79
		style DISSOLVE
	)
	
	(method (init)
		(= north (= east 80))
		(= south 30)
		(= isIndoors FALSE)
		(Load VIEW 80)
		(Load VIEW 60)
		(Load VIEW 140)
		(Load VIEW 142)
		(Load VIEW 143)
		(Load VIEW 144)
		(if isNightTime
			(= picture 179)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego view: 2 posn: 232 91 loop: 1 init:)
			)
			(south
				(if lolotteAlive
					(= h1 (Actor new:))
					(= h2 (Actor new:))
					(h1 setScript: h1Actions)
					(h2 setScript: h2Actions init: hide:)
				)
				(ego x: 63 y: 188)
				(ego init: view: 2)
			)
			(else 
				(ego x: 63 y: 188)
				(= h1 (Actor new:))
				(= h2 (Actor new:))
				(h1 setScript: h1Actions)
				(h2 setScript: h2Actions init: hide:)
				(ego x: 63 y: 188)
				(ego init: view: 2)
			)
		)
		(ego edgeHit: 0)
		(= currentStatus egoNormal)
		(= noWearCrown TRUE)
		(= isHandsOff FALSE)
	)
	
	(method (doit &tmp thisControl)
		(super doit:)
		(if (and (== (curRoom script?) 0) (== currentStatus egoNormal))
			(cond 
				((& (= thisControl (ego onControl: 0)) cBROWN)
					(curRoom newRoom: 80)
				)
				((& thisControl cRED)
					(if (< (ego heading?) 180)
						(ego baseSetter: 0)
					else
						(ego baseSetter: (ScriptID 0 1))
					)
				)
				((& thisControl cFALL)
					(sounds eachElementDo: #stop 0)
					(sFalling play:)
					(stopHench cue:)
					(curRoom
						setScript:
							(cond 
								((& thisControl cBLUE) fallBlue)
								((& thisControl cCYAN) fallCyan)
								((& thisControl cGREEN) fallGreen)
								((& thisControl cLGREY) fallLgrey)
								((& thisControl cGREY) fallGrey)
								((& thisControl cLBLUE) fallLblue)
								((& thisControl cLGREEN) fallLgreen)
								((& thisControl cLCYAN) fallLcyan)
							)
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp index)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((or (Said '/room,cliff') (Said '[<around][/noword]'))
								(Print 79 0)
							)
							((Said '/goon')
								(if (cast contains: h1)
									(Print 79 1)
								else
									(Print 79 2)
								)
							)
							((Said '/path')
								(Print 79 3)
							)
							((Said '/castle')
								(Print 79 4)
							)
							((Said '/boulder')
								(Print 79 5)
							)
							((Said '/dirt,down')
								(Print 79 6)
							)
							((Said '/forest')
								(Print 79 7)
							)
						)
					)
					((Said 'climb/cliff') (Print 79 8))
					((or (Said 'converse/goon') (Said 'converse[/noword]'))
						(Print 79 9)
					)
					((Said 'get,capture/goon')
						(Print 79 10)
					)
					((Said 'deliver>')
						(if (= index (inventory saidMe:))
							(if (ego has: (inventory indexOf: index))
								(Print 79 11)
							else
								(Print 800 2)
							)
						else
							(Print 79 12)
							(event claimed: TRUE)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(cls)
		(if (!= currentStatus egoFalling)
			(= noWearCrown FALSE)
			(ego illegalBits: cWHITE setPri: -1)
			(super newRoom: n)
		)
	)
)

(instance fallBlue of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= currentStatus egoFalling)
				(HandsOff)
				(ego
					setPri: 8
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (- (ego x?) 8) (ego y?)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallCyan of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 8
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) $0001)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (+ (ego y?) 8)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallGreen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 4
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (- (ego y?) 8)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallLgrey of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 10
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (+ (ego x?) 8) (+ (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallGrey of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 7
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) $0001)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (+ (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallLblue of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 6
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (+ (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallLgreen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 0
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (- (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance fallLcyan of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					setPri: 0
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (+ (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 150) self
				)
			)
			(1
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance h1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(henchTheme play:)
				(h1
					ignoreHorizon:
					posn: 57 6
					setStep: 4 3
					view: 142
					setCycle: Walk
					setMotion: MoveTo -10 22 self
					ignoreActors:
					illegalBits: 0
					setPri: 13
					init:
				)
				(Print 79 13
					#draw
					#at -1 10
					#dispose
				)
			)
			(1
				(h1 xStep: 4 yStep: 3 setMotion: MoveTo -10 50 self)
			)
			(2
				(cls)
				(h1
					view: 143
					xStep: 6
					yStep: 4
					setMotion: MoveTo 252 66 self
				)
			)
			(3
				(h1
					view: 144
					xStep: 8
					yStep: 6
					setMotion: Chase ego 14 self
				)
				(h2Actions changeState: 1)
			)
			(4
				(h1
					view: 140
					posn: (+ (ego x?) 14) (+ (ego y?) 5)
				)
				(Face h1 ego)
				(ego loop: 0 setMotion: 0)
				(HandsOff)
			)
		)
	)
)

(instance h2Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(h2
					ignoreHorizon:
					posn: 57 6
					view: 142
					setCycle: Walk
					xStep: 4
					yStep: 3
					setMotion: MoveTo 26 65 self
					ignoreActors:
					illegalBits: 0
					setPri: 13
					show:
				)
			)
			(2
				(h2
					view: 143
					xStep: 6
					yStep: 4
					setMotion: MoveTo 42 120 self
				)
			)
			(3
				(h2
					view: 144
					xStep: 8
					yStep: 6
					setMotion: MoveTo (- (ego x?) 14) (ego y?) self
				)
			)
			(4
				(h2 view: 140)
				(Face h2 ego)
				((ScriptID 0 4) setReal: self 2)
			)
			(5
				(= currentStatus 1)
				(h1 hide:)
				(h2 hide:)
				(cond 
					((== gamePhase getTheUnicorn)
						(self changeState: 20)
					)
					((and (== gamePhase getTheHen) (not (ego has: iMagicHen)))
						(self changeState: 20)
					)
					((and (== gamePhase getPandoraBox) (not (ego has: iPandorasBox)))
						(self changeState: 20)
					)
					(else
						(ego
							view: 60
							illegalBits: 0
							setPri: 15
							ignoreHorizon:
							setLoop: 1
							cel: 255
							setCycle: EndLoop self
						)
					)
				)
			)
			(6
				(ego
					view: 80
					setLoop: 5
					setCycle: Forward
					setMotion: MoveTo 24 60 self
				)
			)
			(7
				(ego
					setLoop: 3
					posn: (ego x?) (- (ego y?) 5)
					setMotion: MoveTo 50 38
				)
				((ScriptID 0 4) setReal: self 2)
			)
			(8
				(ego setLoop: 1 setMotion: MoveTo 72 29)
				((ScriptID 0 4) setReal: self 3)
			)
			(9
				(ego
					illegalBits: 0
					setPri: 15
					setLoop: 0
					setMotion: MoveTo 72 29 self
				)
			)
			(10
				(ego setLoop: -1 setCel: -1)
				(User canControl: TRUE)
				(User canInput: TRUE)
				(ego dispose:)
				(HandsOn)
				(curRoom newRoom: 92)
			)
			(20
				(ego
					illegalBits: 0
					setPri: 15
					view: 60
					setLoop: 2
					cel: 255
					setCycle: EndLoop self
				)
			)
			(21
				(proc0_19 henchTheme 1)
				(ego
					view: 80
					setCycle: Forward
					setLoop: 4
					setMotion: MoveTo -40 160 self
				)
			)
			(22
				(HandsOn)
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance egoDead of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 3)
			)
			(1
				(Print 79 14)
				((ScriptID 0 4) setReal: self 4)
			)
			(2
				(= dead TRUE)
			)
		)
	)
)

(instance stopHench of Script
	(method (cue)
		(if (cast contains: h1)
			(h1
				setLoop: -1
				setCycle: Walk
				setMotion: MoveTo -68 32
				setCycle: Forward
			)
		)
		(if (cast contains: h2)
			(h2
				setLoop: -1
				setCycle: Walk
				setMotion: MoveTo -68 32
				setCycle: Forward
			)
		)
	)
)
