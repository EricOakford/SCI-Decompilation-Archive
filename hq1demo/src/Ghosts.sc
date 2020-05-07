;;; Sierra Script 1.0 - (do not remove this comment)
(script# GHOSTS)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Reverse)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	ghosts 0
)

(local
	local0
	local1
	twisterX
	twisterY
	oldTwisterX
	oldTwisterY
)
(instance riser of Actor
	(properties)
)

(instance longOne of Actor
	(properties)
)

(instance twister of Actor
	(properties)
)

(instance tumbler of Actor
	(properties)
)

(instance swimmer of Actor
	(properties)
)

(instance ghosts of Room
	(properties
		picture 64
		style IRISIN
		horizon 116
	)
	
	(method (init)
		(LoadMany VIEW 64 63)
		(Load SOUND 12)
		(= currentPalette 1)
		(super init:)
		(ego view: 4 loop: 0 cel: 0 posn: 15 177 init:)
		(gravestone init:)
		(etching init:)
		(riser
			view: 64
			setLoop: 3
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			posn: 0 1000
			init:
		)
		(riser setScript: riseUpLeft)
		(longOne
			view: 64
			setLoop: 7
			setPri: 6
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			posn: 21 126
			init:
			setStep: 3 1
		)
		(longOne setScript: peekABoo)
		(twister
			view: 64
			setLoop: 0
			setPri: 14
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			posn: 163 0
			init:
			setStep: 3 3
		)
		(twister setScript: twistIt)
		(tumbler
			view: 64
			setLoop: 8
			setPri: 7
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			posn: 251 39
			init:
			setStep: 3 2
		)
		(tumbler setScript: spinOnTree)
		(swimmer
			view: 64
			setPri: 14
			setLoop: 1
			ignoreActors:
			ignoreHorizon:
			illegalBits: 0
			posn: 27 53
			init:
			cycleSpeed: 2
			setStep: 3 4
		)
		(swimmer setScript: swimRight)
		(music number: 12 play:)
		(self setScript: demoMsg)
	)
	
	(method (dispose)
		(DisposeScript REVERSE)
		(super dispose:)
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
				(riser setMotion: MoveTo 255 117 self)
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
				(riser setScript: riseUpRight)
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
				(riser setMotion: MoveTo -65 (Random 45 115) self)
			)
			(3
				(riser setScript: riseDownLeft)
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
				(longOne setMotion: MoveTo 21 70 self)
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
				(longOne stopUpd: setScript: 0)
			)
		)
	)
)

(instance twistIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(twister
					view: 64
					setLoop: 0
					setPri: 12
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 155 -10
					cycleSpeed: 1
					setCycle: Forward
					startUpd:
					setMotion: MoveTo 200 50 self
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
				(twister setMotion: MoveTo twisterX twisterY self)
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
							((< (twister x?) 30) -20)
							((> (twister x?) 290) 340)
						)
						(if (< (twister y?) 20) -10 else (twister y?))
						self
				)
			)
			(4
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
					setLoop: 8
					setPri: 7
					setStep: 5 3
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: -15 (Random 40 110)
					setCycle: Forward
					startUpd:
				)
				(tumbler setMotion: MoveTo 345 (Random 0 80) self)
			)
			(1
				(tumbler stopUpd: setScript: 0)
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
					view: 64
					setLoop: 8
					setStep: 5 3
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					posn: 253 -10
					setCel: 0
					setMotion: MoveTo 253 39
					startUpd:
				)
				(= cycles 35)
			)
			(1
				(++ local1)
				(tumbler setCel: -1 setCycle: EndLoop self)
			)
			(2
				(if (> local1 (Random 2 4))
					(tumbler setCycle: Forward)
					(tumbler setMotion: MoveTo -15 (Random 40 110) self)
				else
					(self changeState: 1)
				)
			)
			(3
				(tumbler setScript: spinAcross)
			)
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
					setStep: 6 4
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					setLoop: 1
					cycleSpeed: 1
					setCycle: Forward
					posn: -15 (Random 25 65)
					startUpd:
				)
				(swimmer setMotion: MoveTo 350 (Random 85 125) self)
			)
			(1
				(swimmer setScript: swimLeft)
			)
		)
	)
)

(instance swimLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(swimmer
					view: 64
					setStep: 6 4
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					setLoop: 2
					cycleSpeed: 1
					setCycle: Forward
					posn: 325 (Random -10 10)
					startUpd:
				)
				(swimmer
					setMotion: MoveTo (Random 115 145) (Random 55 85) self
				)
			)
			(1
				(swimmer setMotion: MoveTo (Random 30 65) -10 self)
			)
			(2
				(swimmer stopUpd: setScript: 0)
			)
		)
	)
)

(instance demoMsg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(Print 6 0
					#at -1 185
					#width 320
					#mode teJustCenter
					#dispose
					#window aBoo
				)
				(= seconds 10)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(= seconds 6)
			)
			(3 (curRoom newRoom: BABA))
		)
	)
)

(instance etching of View
	(properties
		y 133
		x 227
		view 63
	)
)

(instance gravestone of View
	(properties
		y 119
		x 124
		view 63
		loop 2
		priority 6
	)
)

(instance aBoo of SysWindow
	(properties
		color vLMAGENTA
		back vBLACK
	)
)

(instance ghostMusic of Sound
	(properties
		number 12
		loop -1
	)
)
