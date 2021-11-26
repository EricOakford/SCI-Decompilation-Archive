;;; Sierra Script 1.0 - (do not remove this comment)
(script# 133)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm133 0
)

(local
	bains
	marie
	chair
	controlPanel
	gauge
	printObj
	[local6 100]
	local106
	local107
	egoHiding
	marieIsCalm
	marieSeesEgo
	marieTiedUp
	bainsIsHere
	local113
	bainsSeesEgo
	local115
)
(procedure (localproc_0060)
	(Print &rest #at -1 20 #font smallFont)
)

(instance themeMusic of Sound
	(properties
		priority 7
	)
)

(instance bainsGunShot of Sound
	(properties
		number 41
		priority 8
	)
)

(instance rm133 of Room
	(properties
		picture 77
		style $0006
	)
	
	(method (init)
		(Load rsVIEW 297)
		(Load rsVIEW 37)
		(Load rsVIEW 38)
		(Load rsVIEW 13)
		(Load rsVIEW 15)
		(Load rsVIEW 274)
		(Load rsVIEW 285)
		(Load rsSOUND 41)
		(Load rsSOUND 38)
		(Load rsSOUND 33)
		(Load rsSOUND 14)
		(Load rsSOUND 47)
		(super init:)
		(ego view: (if gunDrawn 7 else 1) posn: 160 170 init:)
		(HandsOn)
		(= marieTiedUp 1)
		(= gunNotNeeded 0)
		(= gunFireState 0)
		((= bains (Actor new:))
			view: 13
			loop: 2
			cel: 0
			illegalBits: 0
			posn: 145 205
			setCycle: Walk
			init:
			hide:
		)
		((= marie (Actor new:))
			view: 38
			loop: 2
			cel: 0
			posn: 210 115
			setCycle: Forward
			cycleSpeed: 2
			setPri: 8
			init:
		)
		((= chair (Actor new:))
			view: 38
			loop: 3
			cel: 0
			ignoreActors: 1
			posn: 210 115
			init:
			setPri: 8
			addToPic:
		)
		((= controlPanel (Prop new:))
			view: 297
			loop: 0
			cel: 0
			posn: 155 64
			setPri: 1
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= gauge (Prop new:))
			view: 297
			loop: 1
			cel: 0
			posn: 184 75
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors: 1
			init:
		)
		(if (< howFast 30)
			(controlPanel stopUpd:)
			(gauge stopUpd:)
		)
		(self setScript: rm133Script)
	)
	
	(method (doit)
		(cond 
			((& (ego onControl: 1) $0002) (cSound number: 9 loop: -1 play:) (curRoom newRoom: 131))
			(sewerCutscene 0)
			((& (ego onControl: 1) $0100) (= egoHiding 1))
			(else (= egoHiding 0))
		)
		(cond 
			((not marieTiedUp) 0)
			((> (ego distanceTo: marie) 40) (marie cycleSpeed: 3))
			((not marieSeesEgo)
				(localproc_0060 133 0)
				(marie cycleSpeed: 1)
				(= marieSeesEgo 1)
			)
			(else (marie cycleSpeed: 1))
		)
		(if global205
			(if (== global205 2) (++ local107))
			(= global205 0)
		)
		(if
		(and bainsSeesEgo (not local113) (not local115))
			(if (not egoHiding)
				(= local113 1)
				(HandsOff)
				(rm133Script changeState: 18)
			else
				(= local113 1)
				(rm133Script changeState: 21)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look[<at,around][/(!*,chamber)]') (localproc_0060 133 1))
					((Said 'look/gauge') (localproc_0060 133 2))
					((Said 'look/panel') (localproc_0060 133 3))
					((Said 'press/button') (localproc_0060 133 4))
					((Said 'use/control') (localproc_0060 133 5))
					((Said 'listen') (localproc_0060 133 6))
					((Said 'smell[/*]') (localproc_0060 133 7))
					((Said 'remove/mask')
						(if (ego has: 32)
							(localproc_0060 133 8)
						else
							(localproc_0060 133 9)
						)
					)
					((Said 'wear/mask')
						(if (ego has: 32)
							(localproc_0060 133 10)
						else
							(localproc_0060 133 9)
						)
					)
					((Said 'help/cheeks') (localproc_0060 133 11))
					(
					(or (Said 'untie/(cheeks,rope)') (Said 'remove/rope'))
						(cond 
							((not marieTiedUp) (Print 133 12))
							((ego inRect: 185 110 224 135)
								(Bset 125)
								(SolvePuzzle 5 160)
								(rm133Script changeState: 1)
							)
							(else (localproc_0060 133 13))
						)
					)
					(
						(or
							(Said '(keep,be)<calm,calm')
							(Said 'close<up')
							(Said 'calm/cheeks')
						)
						(cond 
							(marieIsCalm (localproc_0060 133 14))
							((not marieTiedUp) (Print 133 15))
							((> (ego distanceTo: marie) 40) (Print 133 16))
							(else
								(localproc_0060 133 17)
								(= marieIsCalm 1)
								(marie setCycle: 0)
								(Bset 125)
								(SolvePuzzle 5 161)
							)
						)
					)
					((Said 'look/cheeks')
						(cond 
							(marieTiedUp (localproc_0060 133 18))
							((< (rm133Script state?) 4) (localproc_0060 133 19))
							((< (rm133Script state?) 28) (localproc_0060 133 20))
							(else (localproc_0060 133 21))
						)
					)
					((Said '*/cheeks')
						(if marieTiedUp
							(localproc_0060 133 22)
						else
							(localproc_0060 133 23)
						)
					)
					((Said 'remove/gag')
						(if (not marieTiedUp)
							(localproc_0060 133 24)
						else
							(localproc_0060 133 25)
						)
					)
					(
						(or
							(Said '(freeze,police,cease)')
							(Said 'say/(freeze,police,cease)')
						)
						(if (not bainsIsHere)
							(Print 133 26)
						else
							(localproc_0060 133 27)
							(rm133Script changeState: 21)
							(= local113 1)
						)
					)
				)
			)
		)
	)
)

(instance rm133Script of Script
	(properties)
	
	(method (init)
		(cSound fade:)
		(if (not (Btst 122))
			(localproc_0060 133 28)
			(Bset 125)
			(SolvePuzzle 10 122)
			(localproc_0060 133 29)
			(localproc_0060 133 30)
		)
		(themeMusic number: 14 loop: 1 play:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(if gunDrawn (AssignObjectToScript ego holsterGun))
				(localproc_0060 133 31)
				(marie setLoop: 1 cel: 1 posn: 215 115 setCycle: 0)
				(ego ignoreActors: 1 setMotion: MoveTo 190 115 self)
			)
			(2
				(ego ignoreActors: 0 loop: 0 cel: 0 posn: 190 115)
				(marie ignoreActors: 0 setCycle: EndLoop self)
			)
			(3
				(localproc_0060 133 32)
				(localproc_0060 133 33)
				(marie setLoop: 0 cel: 0 posn: 197 115 setCycle: EndLoop self)
				(ego hide:)
			)
			(4
				(= marieTiedUp 0)
				(= untiedMarie 1)
				(marie setCel: 4 setCycle: BegLoop)
				(RedrawCast)
				(if (not marieIsCalm)
					(User canInput: 0)
					(localproc_0060 133 34)
					(localproc_0060 133 35)
					(bains show: setMotion: MoveTo 145 150 self)
					(themeMusic stop: number: 33 play:)
				else
					(User canInput: 1)
					(localproc_0060 133 36)
					(localproc_0060 133 37)
					(self changeState: 8)
				)
			)
			(5
				(ego show:)
				(marie view: 37 loop: 1 posn: 204 115 ignoreActors: 0)
				(localproc_0060 133 38)
				(localproc_0060 133 39)
				(localproc_0060 133 40)
				(bains
					view: 15
					setLoop: 0
					cel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
				(bainsGunShot play:)
			)
			(6
				(bains cel: 0 setCycle: EndLoop)
				(bainsGunShot play:)
				(HandsOff)
				(ego
					view: 297
					setMotion: 0
					loop: (if (> (bains x?) (ego x?)) 2 else 3)
					setPri: 2
					setCycle: EndLoop self
				)
			)
			(7 (EgoDead 133 41))
			(8
				(localproc_0060 133 42)
				(themeMusic number: 38 loop: -1 play:)
				(localproc_0060 133 43)
				(localproc_0060 133 44)
				(= cycles 2)
			)
			(9
				(ego show:)
				(marie
					view: 38
					loop: 1
					setCel: 255
					posn: 215 115
					setCycle: CycleTo 1 -1
				)
				(HandsOn)
				(= cycles 100)
			)
			(10
				(bains show:)
				(if (<= (ego y?) 170)
					(bains setMotion: MoveTo 145 170 self)
				else
					(bains setMotion: MoveTo 145 195 self)
				)
				(themeMusic stop: number: 33 play:)
				(= bainsIsHere 1)
				(= local107 0)
			)
			(11
				(if (== egoHiding 1)
					(bains setMotion: MoveTo 150 145 self)
				else
					(self changeState: 17)
				)
			)
			(12
				(bains setMotion: MoveTo 160 130 self)
				(= bainsSeesEgo 1)
			)
			(13 (= seconds 5))
			(14
				(bains setMotion: 0)
				(bains loop: 2)
				(= seconds 1)
			)
			(15
				(bains loop: 1)
				(= seconds 1)
			)
			(16 (self changeState: 18))
			(17
				(= local106 (- (bains x?) (ego x?)))
				(if
				(and (<= (ego y?) 150) (>= (Abs local106) 20))
					(bains setMotion: MoveTo 145 160 self)
				else
					(self changeState: 18)
				)
			)
			(18
				(bains setMotion: 0)
				(cond 
					((<= (+ (bains x?) 20) (ego x?)) (bains view: 15 setLoop: 0 cel: 0 setCycle: EndLoop self))
					((>= (- (bains x?) 20) (ego x?)) (bains view: 15 setLoop: 1 cel: 0 setCycle: EndLoop self))
					(else (bains view: 15 setLoop: 3 cel: 0 setCycle: EndLoop self))
				)
			)
			(19
				(HandsOff)
				(= sewerCutscene 1)
				(if egoHiding
					(ego
						view: 297
						loop: (if (> (bains x?) (ego x?)) 2 else 3)
						posn: 82 138
						setCycle: EndLoop self
					)
				else
					(ego
						view: 297
						loop: (if (> (bains x?) (ego x?)) 2 else 3)
						setCycle: EndLoop self
					)
				)
				(ego setMotion: 0)
				(bains setMotion: 0)
			)
			(20
				(HandsOff)
				(cond 
					((and (== local107 0) (not gunDrawn)) (EgoDead 133 45))
					((not egoHiding) (EgoDead 133 46))
					(else (EgoDead 133 47))
				)
			)
			(21
				(= local107 0)
				(bains
					view: 15
					setMotion: 0
					setLoop: 1
					cel: 0
					setCycle: EndLoop
				)
				(= cycles 6)
				(bainsGunShot play:)
			)
			(22
				(if (<= local107 1)
					(bains view: 15 setLoop: 1 cel: 0 setCycle: EndLoop)
					(bainsGunShot play:)
					(= cycles 6)
				else
					(self changeState: 25)
				)
			)
			(23
				(if (<= local107 1)
					(bains view: 15 setLoop: 1 cel: 0 setCycle: EndLoop)
					(bainsGunShot play:)
					(= cycles 6)
				else
					(self changeState: 25)
				)
			)
			(24 (self changeState: 19))
			(25
				(HandsOff)
				(themeMusic dispose:)
				(bainsGunShot dispose:)
				(ego setMotion: 0)
				(SolvePuzzle 15)
				(bains view: 274 setLoop: 0 cel: 0 setCycle: EndLoop)
				(= local115 1)
				(= cycles 10)
			)
			(26
				(HandsOff)
				(if gunDrawn (AssignObjectToScript ego holsterGun))
				(bains ignoreActors: 1 stopUpd:)
				(= printObj (localproc_0060 133 48 88))
				(= seconds 8)
			)
			(27
				(cls)
				(= printObj (localproc_0060 133 49 88))
				(ego
					view: 1
					setMotion: MoveTo (+ (bains x?) 8) (- (bains y?) 15) self
				)
			)
			(28
				(cls)
				(ego loop: 2)
				(RedrawCast)
				(themeMusic stop: number: 47 loop: 1 play:)
				(= seconds 3)
			)
			(29
				(= printObj (localproc_0060 133 50 88))
				(= seconds 7)
			)
			(30
				(cls)
				(= printObj (localproc_0060 133 51 88))
				(= seconds 13)
			)
			(31
				(cls)
				(= printObj (localproc_0060 133 52 88))
				(= seconds 8)
			)
			(32
				(cls)
				(marie
					view: 38
					loop: 1
					setCel: 0
					posn: 215 115
					setCycle: EndLoop
					ignoreActors: 1
				)
				(ego setMotion: MoveTo 190 115 self)
			)
			(33
				(marie
					view: 38
					loop: 0
					cel: 0
					posn: 197 115
					setCycle: EndLoop self
				)
				(ego hide:)
			)
			(34
				(RedrawCast)
				(localproc_0060 133 53 88)
				(= seconds 5)
			)
			(35
				(if (!= (themeMusic prevSignal?) -1)
					(-- state)
					(= cycles 2)
				else
					(cls)
					;(cSound stop: dispose:) ;causes memory fragment 
					(curRoom newRoom: 91)
				)
			)
		)
	)
)

(instance holsterGun of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view:
					(switch (ego view?)
						(7 5)
						(5 5)
					)
					loop: (mod (ego loop?) 4)
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(1
				(= gunDrawn 0)
				(ego view: 1 setCel: 0 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)
