;;; Sierra Script 1.0 - (do not remove this comment)
(script# regSewer)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	sewer 0
)

(local
	[str 100]
	egoX
	egoY
)

(define cFALL 60) ;(| cGREEN cCYAN cRED cMAGENTA)

(procedure (localproc_000c obj)
	(return
		(cond 
			((== (obj cel?) 3)
				(return FALSE)
			)
			(
				(and
					(> (- (obj x?) (ego x?)) 20)
					(== (ego loop?) 0)
				)
				(return TRUE)
			)
			(
				(and
					(< (- (obj x?) (ego x?)) -20)
					(== (ego loop?) 1)
				)
				(return TRUE)
			)
			(else
				(return FALSE)
			)
		)
	)
)

(instance lightObj of Prop
	(properties
		view 99
		loop 6
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said '/light>')
					(cond 
						((Said 'get')
							(Print 205 0)
						)
						((Said 'turn<(on,off)')
							(Print 205 1)
						)
					)
				)
			)
		)
	)
)

(instance ratScript of Script
	(method (changeState newState obj)
		(switch (= state newState)
			(1
				(switch (obj name?)
					(1
						(obj setLoop: 7 setMotion: MoveTo 102 91 obj)
					)
					(2
						(obj setLoop: 2 setMotion: MoveTo 249 65 obj)
					)
					(3
						(obj setLoop: 7 setMotion: MoveTo 72 95 obj)
					)
					(4
						(obj setLoop: 3 setMotion: MoveTo 70 66 obj)
					)
					(5
						(obj setLoop: 6 setMotion: MoveTo 212 86 obj)
					)
					(6
						(obj setLoop: 6 setMotion: MoveTo 232 93 obj)
					)
					(7
						(obj setLoop: 3 setMotion: MoveTo 287 57 obj)
					)
				)
			)
			(2
				(switch (obj name?)
					(1
						(obj setLoop: 5 setMotion: MoveTo -100 172 obj)
					)
					(2
						(obj setLoop: 3 setMotion: MoveTo 400 65 obj)
					)
					(3
						(obj setLoop: 5 setMotion: MoveTo -100 167 obj)
					)
					(4
						(obj setLoop: 2 setMotion: MoveTo -100 66 obj)
					)
					(5
						(obj setLoop: 4 setMotion: MoveTo 400 163 obj)
					)
					(6
						(obj setLoop: 4 setMotion: MoveTo 400 149 obj)
					)
					(7
						(obj setLoop: 2 setMotion: MoveTo -100 57 obj)
					)
				)
			)
		)
	)
)

(instance ratObj of Actor
	(properties
		view 260
		illegalBits $0000
		xStep 2
		moveSpeed 1
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said '/mice>')
					(cond 
						((Said 'look')
							(Print 205 2)
						)
						((Said 'get,apprehend')
							(Print 205 3)
						)
						((Said 'kill')
							(Print 205 4)
						)
						((Said 'eat')
							(Print 205 5)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if (== (ratScript state?) 1)
			(ratScript changeState: 2 self)
		else
			(ratScript changeState: 1 self)
		)
	)
)

(instance sewer of Region
	(method (init)
		(super init:)
		(= sewerRat ratObj)
		(= sewerLight lightObj)
		(if
			(or
				(== curRoomNum 123)
				(== curRoomNum 130)
				(== curRoomNum 120)
				(== curRoomNum 124)
				(== curRoomNum 129)
				(== curRoomNum 125)
				(== curRoomNum 132)
				(== curRoomNum 127)
				(== curRoomNum 122)
				(== curRoomNum 128)
			)
			(= sewerLight2 (sewerLight new:))
		)
		(if (== methaneGasTimer 0)
			(= methaneGasTimer -1)
			(= gMethaneGasTimer -1)
		)
		(Load VIEW 0)
		(Load VIEW 4)
		(Load VIEW 6)
		(Load VIEW 99)
		(Load VIEW 260)
		(Load SOUND 41)
		(if (ego has: iGasMask)
			(Load VIEW 296)
			(Load VIEW 305)
			(Load VIEW 306)
		)
		(= gunFireState 0)
		(= gunNotNeeded FALSE)
	)
	
	(method (doit)
		(if (!= methaneGasTimer gMethaneGasTimer)
			(if (== methaneGasTimer -1)
				(cSound
					stop:
					number: 9
					priority: 6
					loop: -1
					play:
				)
			)
			(= gMethaneGasTimer methaneGasTimer)
		)
		(if global205
			(= global205 0)
			(cond 
				((!= methaneGasTimer -1)
					(pBoomScript changeState: 1)
				)
				((not (cast contains: sewerLight2))
					(if (localproc_000c sewerLight)
						(sewerLight startUpd: setCycle: EndLoop)
					)
				)
				(
					(and
						(localproc_000c sewerLight)
						(localproc_000c sewerLight2)
					)
					(if
						(<=
							(ego distanceTo: sewerLight)
							(ego distanceTo: sewerLight2)
						)
						(sewerLight setCycle: EndLoop)
					else
						(sewerLight2 setCycle: EndLoop)
					)
				)
				((localproc_000c sewerLight)
					(sewerLight setCycle: EndLoop)
				)
				((localproc_000c sewerLight2)
					(sewerLight2 setCycle: EndLoop)
				)
			)
		)
		(if
			(and
				(> methaneGasTimer 0)
				(not wearingGasMask)
				(not sewerCutscene)
			)
			(switch (-- methaneGasTimer)
				(0
					(asphyxiation changeState: 0)
				)
				(20
					(Print 205 33)
				)
				(40
					(Print 205 34)
				)
				(60
					(Print 205 35)
					(cSound number: 32 loop: -1 priority: 6 play:)
				)
			)
		)
		(if (and (& (ego onControl: origin) cFALL) (not sewerCutscene))
			(HandsOff)
			(= sewerCutscene TRUE)
			(ego
				view: 99
				setLoop: 4
				setStep: 5 2
				setMotion: 0
				illegalBits: 0
				setScript: fallInSewer
			)
			(cSound
				stop:
				number: 24
				loop: 1
				priority: 12
				play:
			)
			(switch (ego onControl: origin)
				(cGREEN
					(fallInSewer changeState: 1)
				)
				(cCYAN
					(fallInSewer changeState: 2)
				)
				(cRED
					(fallInSewer changeState: 3)
				)
				(cMAGENTA
					(fallInSewer changeState: 4)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[<at,around][/(noword,chamber)]')
						(Print
							(Format @str 205 6
								(if
									(and
										(!= curRoomNum 122)
										(!= curRoomNum 123)
										(!= curRoomNum 125)
										(!= curRoomNum 127)
										(!= curRoomNum 129)
									)
									{ Rats scurry about the sewer pipes.}
								else
									{}
								)
							)
						)
						(if
							(or
								(== curRoomNum 121)
								(== curRoomNum 126)
								(== curRoomNum 130)
							)
							(Print 205 7)
						)
						(if
							(or
								(== curRoomNum 120)
								(== curRoomNum 124)
								(== curRoomNum 128)
							)
							(Print 205 8)
						)
					)
					((Said '/slime')
						(Print 205 9)
					)
					((Said '/bridge')
						(Print 205 10)
					)
					((Said '/pipe<drain')
						(if (or (== curRoomNum 121) (== curRoomNum 126))
							(Print 205 11)
						else
							(Print 205 12)
						)
					)
					((Said '/drain')
						(if (or (== curRoomNum 121) (== curRoomNum 126))
							(Print 205 13)
						else
							(Print 205 14)
						)
					)
					((Said '/faucet')
						(if
							(or
								(== curRoomNum 124)
								(== curRoomNum 126)
								(== curRoomNum 132)
								(== curRoomNum 121)
							)
							(Print 205 15)
						else
							(Print 205 16)
						)
					)
					((Said '/wall')
						(Print 205 17)
					)
					((Said '/bug')
						(Print 205 18)
					)
					((Said '/pipe')
						(Print 205 19)
					)
					((Said '[<at,down][/dirt,floor]')
						(Print 205 20)
					)
					((Said '[<at,up][/ceiling]')
						(Print 205 21)
					)
					((Said '/sewer,water')
						(Print 205 22)
					)
					((Said '/light')
						(Print
							(Format @str 205 23
								(if (== (sewerLight cel?) 3)
									{has been shot out.}
								else
									{is on.}
								)
								(cond 
									((not (cast contains: sewerLight2)) {})
									((== (sewerLight2 cel?) 3) {The other light has been shot out.})
									(else {The other light is on.})
								)
							)
						)
					)
				)
			)
			((Said 'listen')
				(Print 205 24)
			)
			((Said 'smell[/anyword]') 
				(Print 205 25)
			)
			(
				(or
					(Said 'extender')
					(Said 'use/extender,talkie,walkie')
				)
				(if (ego has: iWalkieTalkie)
					(Print 205 26)
				else
					(DontHave)
				)
			)
			((Said 'turn,use/faucet')
				(if
					(or
						(== curRoomNum 121)
						(== curRoomNum 124)
						(== curRoomNum 126)
						(== curRoomNum 132)
					)
					(Print 205 27)
				else
					(Print 205 16)
				)
			)
			((Said 'remove/mask')
				(cond 
					((not (ego has: iGasMask))
						(Print 205 28)
					)
					((not wearingGasMask)
						(Print 205 29)
					)
					(else
						(= wearingGasMask FALSE)
						(if (== (ego view?) 296)
							(ego view: 0)
						else
							(ego view: 6)
						)
					)
				)
			)
			((Said 'wear,deposit/mask')
				(cond 
					((not (ego has: iGasMask))
						(Print 205 30)
					)
					((== methaneGasTimer -1)
						(Print 205 31)
					)
					(wearingGasMask
						(Print 205 32)
					)
					(else
						(= wearingGasMask TRUE)
						(if (== (ego view?) 0)
							(ego view: 296)
						else
							(ego view: 306)
						)
					)
				)
			)
		)
	)
)

(instance fallInSewer of Script
	(method (changeState newState &tmp curPic)
		(= curPic (curRoom picture?))
		(switch (= state newState)
			(1
				(cond 
					((and (!= curPic 204) (!= curPic 201))
						(= egoX 10)
					)
					((<= (ego y?) 100)
						(= egoX 20)
					)
					(else
						(= egoX 45)
					)
				)
				(self changeState: 5)
			)
			(2
				(cond 
					((and (!= curPic 200) (!= curPic 203))
						(= egoX -20)
					)
					((<= (ego y?) 100)
						(= egoX -30)
					)
					(else
						(= egoX -45)
					)
				)
				(ego setLoop: 5)
				(self changeState: 5)
			)
			(3
				(cond 
					((or (== curPic 200) (== curPic 203))
						(= egoX -15)
					)
					((or (== curPic 201) (== curPic 204))
						(= egoX 15)
					)
				)
				(= egoY -2)
				(self changeState: 5)
			)
			(4
				(cond 
					((or (== curPic 200) (== curPic 203))
						(= egoX 20)
					)
					((or (== curPic 201) (== curPic 204))
						(= egoX -20)
					)
				)
				(= egoY 20)
				(self changeState: 5)
			)
			(5
				(ego
					view: 99
					setCel: 0
					setMotion: MoveTo (+ (ego x?) egoX) (+ (ego y?) egoY)
					setCycle: CycleTo 9 1 self
				)
			)
			(6
				(ego hide:)
				(= cycles 60)
			)
			(7
				(EgoDead
					{ Falling into the trough of human waste,
					 you lose consciousness as you slip beneath the surface.}
				)
			)
		)
	)
)

(instance asphyxiation of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= sewerCutscene TRUE)
				(ego
					view: 298
					loop: 5
					cel: 0
					findPosn:
					setCycle: EndLoop self
				)
			)
			(1
				(Print 205 36)
				(Print 205 37)
				(EgoDead
					{You die from asphyxiation in a pocket of
					 methane gas. Next time, avoid this area or ...?}
				)
			)
		)
	)
)

(instance explodeSound of Sound
	(properties
		number 30
	)
)

(instance pBoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Load PICTURE 300)
				(Load SOUND 30)
				(sounds eachElementDo: #dispose)
				(DrawPic 300 IRISIN)
				(cast eachElementDo: #hide)
				(explodeSound play:)
				(RedrawCast)
				(ShakeScreen 10)
				(DrawPic (curRoom picture?))
				(addToPics eachElementDo: #doit)
				(cast eachElementDo: #show)
				(ego view: 298 loop: 2 cel: 0 findPosn: setCycle: Forward)
				(RedrawCast)
				(Wait 80)
				(EgoDead
					{You're fried! The spark from your gunfire in explosive gas is VERY fatal.}
				)
			)
		)
	)
)
