;;; Sierra Script 1.0 - (do not remove this comment)
(script# 201)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(procedure (localproc_0fc6 param1)
	(Printf 201 16 param1)
)

(procedure (localproc_0fd4)
	(Print 201 3)
)

(procedure (localproc_0fdf param1)
	(Printf 201 17 param1)
)

(procedure (localproc_0fed param1)
	(Printf 201 18 param1)
)

(procedure (localproc_0ffb param1 param2)
	(cast eachElementDo: #hide)
	(param1 show:)
	(param2 show:)
	(DrawPic 66 dpOPEN_CENTEREDGE)
	(Print 201 19 #at 120 32)
	(DrawPic curRoomNum dpOPEN_EDGECENTER)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(param1 hide:)
	(param2 hide:)
	(ego hide:)
)

(class ElevGate of Prop
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		chainID 0
		elevatorID 0
		downID 0
		upID 0
		msgID 0
		gateStMask 0
		gateX 283
		gateY 125
		elevX 296
		elevY 125
		chainY 0
		upRoomNo 0
		downRoomNo 0
	)
	
	(method (init &tmp elevatorIDLastCel temp1)
		(super init:)
		(= global110 -1)
		(switch curRoomNum
			(32
				(= gateStMask 1)
				(= upRoomNo 42)
				(= downRoomNo -1)
			)
			(42
				(= gateStMask 2)
				(= elevY (= gateY 126))
				(= upRoomNo 75)
				(= downRoomNo 32)
			)
			(75
				(= gateStMask 4)
				(= gateX 39)
				(= gateY 112)
				(= elevX 52)
				(= elevY 112)
				(= upRoomNo -1)
				(= downRoomNo 42)
			)
		)
		(= chainY (- elevY 59))
		(self view: 242 loop: 2 posn: gateX gateY stopUpd:)
		(= cel
			(if
				(and
					(& global109 gateStMask)
					(not (& global109 $0010))
					(!= global111 curRoomNum)
				)
				(self lastCel:)
			else
				(= global109 (& global109 (~ gateStMask)))
				0
			)
		)
		(elevatorID
			view: 242
			setPri: 6
			ignoreActors: 1
			illegalBits: 0
		)
		(ego init:)
		(if (== (& global111 $7fff) curRoomNum)
			(elevatorID x: elevX)
			(downID
				view: 166
				loop: 0
				cel: 0
				x: 72
				y: 177
				ignoreActors: 1
				init:
				hide:
			)
			(upID
				view: 166
				loop: 0
				cel: 1
				x: 139
				y: 151
				ignoreActors: 1
				init:
				hide:
			)
			(= elevatorIDLastCel 0)
			(if (== global111 curRoomNum)
				(Load rsPIC 66)
				(LoadMany 132 9 66 79 80 81 89 90 103)
				(elevatorID loop: 0 y: elevY stopUpd:)
				(cSound stop: prevSignal: 0)
			else
				(ego
					posn: elevX (- elevY 2)
					illegalBits: 0
					ignoreActors: 1
					hide:
				)
				(if (< prevRoomNum curRoomNum)
					(= temp1 50)
					(chainID
						view: 242
						setLoop: 3
						ignoreActors: 1
						illegalBits: 0
						posn: elevX (+ chainY 50)
						setPri: 6
						moveSpeed: 1
						setMotion: MoveTo elevX (- elevY 50)
						init:
					)
				else
					(= temp1 -52)
				)
				(= global111 (& global111 $7fff))
				(= global109 (| global109 $0040))
				(HandsOff)
				(= global110 0)
				(elevatorID
					setLoop: 1
					y: (+ elevY temp1)
					moveSpeed: 1
					setMotion: MoveTo elevX elevY self
				)
				(= elevatorIDLastCel (elevatorID lastCel:))
			)
			(elevatorID cel: elevatorIDLastCel init:)
		else
			(Load rsVIEW 5)
			(if (& global109 $0020)
				(HandsOff)
				(if (== curRoomNum 32) (= temp1 0) else (= temp1 60))
				(= global110 15)
				(ego
					view: 5
					setLoop: 4
					setCel: 0
					ignoreActors: 1
					illegalBits: 0
					posn: elevX (- elevY 60)
					setPri: 6
					setStep: 0 7
					moveSpeed: 0
					setMotion: MoveTo elevX (+ elevY temp1) self
				)
			)
			(cond 
				((== curRoomNum 32)
					(self setPri: 9 ignoreActors: 1)
					(elevatorID
						loop: 4
						cel: (* (& global109 gateStMask) 1)
						posn: gateX gateY
						ignoreActors: 0
						stopUpd:
					)
				)
				((< global111 curRoomNum) (elevatorID loop: 3 posn: elevX (+ elevY 2)))
			)
			(elevatorID init:)
		)
	)
	
	(method (doit)
		(if
			(and
				(not (& global109 $0010))
				(not (& global109 $0020))
			)
			(cond 
				((& (ego onControl: 1) $0010)
					(= global110 15)
					(= global109 (| global109 $0020))
					(HandsOff)
					(ego
						view: 5
						setLoop: 4
						setCel: 0
						setPri: 6
						ignoreActors: 1
						illegalBits: 0
						setStep: 0 7
						moveSpeed: 0
						setMotion: MoveTo elevX (+ elevY 60) self
					)
					(cSound number: 9 loop: -1 play:)
				)
				(
					(and
						(& (ego onControl: 1) $0008)
						(== curRoomNum global111)
					)
					(self elevatorFunc:)
				)
			)
		)
		(if (== (& global109 $0050) 16)
			(cond 
				((> (ego y?) (- gateY 2)) (ego posn: elevX (- elevY 2)) (self elevatorFunc:))
				((!= (ego x?) elevX) (ego posn: elevX (- elevY 2)))
			)
		)
		(if
			(and
				(== (cSound number?) 66)
				(== (cSound prevSignal?) -1)
			)
			(cSound
				prevSignal: 0
				number: 103
				loop: -1
				priority: 15
				play:
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(cond 
				(
					(and
						(!= curRoomNum global111)
						(or (Said '*/elevator,lift') (Said 'lift[<open][/*]'))
					)
					(Print 201 0)
				)
				((Said 'examine>')
					(cond 
						((Said '/archway') (Print 201 1))
						((Said '<in,in,in/elevator,lift')
							(if (& global109 $0010)
								(Print 201 2)
							else
								(Print 201 3)
							)
						)
						((Said '/elevator,lift') (Print 201 4))
						((Said '/control')
							(cond 
								((!= curRoomNum global111) (CantDo))
								((& global109 $0010) (localproc_0ffb downID upID))
								(else (Print 201 3))
							)
						)
						((Said '/keyhole')
							(cond 
								((!= curRoomNum global111) (CantDo))
								((& global109 $0010) (Print 201 5))
								(else (Print 201 3))
							)
						)
						((Said '/shaft')
							(cond 
								((< curRoomNum global111) (localproc_0fdf {up}))
								((> curRoomNum global111) (localproc_0fdf {down}))
								((not (& (ego onControl: 1) $0028)) (NotClose))
							)
						)
					)
				)
				(
					(or
						(Said 'open/archway,elevator')
						(Said 'lift[<open][/*]')
					)
					(self elevatorFunc:)
				)
				((Said 'enter/elevator,lift')
					(if (& global109 $0010)
						(localproc_0fed {ARE})
					else
						(self elevatorFunc:)
					)
				)
				((Said 'exit/elevator,lift')
					(if (& global109 $0010)
						(self elevatorFunc:)
					else
						(localproc_0fed {AREN'T})
					)
				)
				((Said 'close/archway,elevator,lift')
					(cond 
						((not (& global109 gateStMask)) (AlreadyClosed))
						((& (ego onControl: 1) $0008)
							(if (and (== (ego y?) gateY) (== curRoomNum 32))
								(Print 201 6)
								(return)
							)
							(self gateFunc: 0 3)
						)
						((& (ego onControl: 1) $0020) (self gateFunc: 0 2))
						(else (NotClose))
					)
				)
				((Said 'get/control') (Print 201 7))
				(
					(or
						(Said '(press,drag,move)<up/control,handle')
						(Said 'up[/!*]')
						(Said '(go,press,drag,move)<up[/!*]')
					)
					(if (& global109 $0010)
						(if
							(and
								(!= upRoomNo -1)
								(or (!= upRoomNo 75) (& global109 $0008))
							)
							(= global109 (| global109 $0040))
							(= global110 9)
							(self cue:)
						else
							(localproc_0fc6 {push})
						)
					else
						(localproc_0fd4)
					)
				)
				(
					(or
						(Said '(press,drag,move)<down/control,handle')
						(Said 'down[/!*]')
						(Said '(go,press,drag,move)<down[/!*]')
					)
					(if (& global109 $0010)
						(if (!= downRoomNo -1)
							(= global109 (| global109 $0040))
							(= global110 12)
							(self cue:)
						else
							(localproc_0fc6 {pull})
						)
					else
						(localproc_0fd4)
					)
				)
				((Said '(use,press,drag,move)/control,handle') (Print 201 8))
				((Said 'latch/elevator,lift,control')
					(if (& global109 $0010)
						(if (ego has: 18)
							(if (& global109 $0008)
								(Print 201 9)
								(= global109 (& global109 $fff7))
							else
								(Print 201 10)
							)
						else
							(Print 201 11)
						)
					else
						(localproc_0fd4)
					)
				)
				(
					(or
						(Said 'unbar[<use<key]/elevator,lift,control')
						(Said 'attach/key/keyhole,eyehole,control')
					)
					(if (& global109 $0010)
						(if (ego has: 18)
							(if (& global109 $0008)
								(Print 201 12)
							else
								(Print 201 9)
								(= global109 (| global109 $0008))
							)
						else
							(Print 201 11)
						)
					else
						(localproc_0fd4)
					)
				)
				((Said 'attach/key') (Print 201 13))
			)
		)
	)
	
	(method (cue)
		(switch (++ global110)
			(0
				(self stopUpd:)
				(= global109 (& global109 $ffbf))
				(if (!= curRoomNum global111) (= global110 -1))
				(HandsOn)
			)
			(1
				(cSound number: 80 loop: 1 play:)
				(chainID stopUpd:)
				(elevatorID cycleSpeed: 2 setCycle: Beg self)
				(if (and (== curRoomNum 42) (& global109 $0008))
					(HandsOn)
					(= global110 -1)
				)
			)
			(2
				(cSound fade:)
				(HandsOff)
				(if (& global109 gateStMask)
					(self cue:)
				else
					(self gateFunc: 1 -1)
				)
			)
			(3
				(self stopUpd:)
				(elevatorID loop: 0 cel: 0 stopUpd:)
				(ego
					view: 0
					loop: 2
					posn: (- elevX 1) (- elevY 3)
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo (+ elevX 1) (+ elevY 2) self
					init:
					show:
				)
			)
			(4 (self gateFunc: 0 3))
			(5
				(if (& (ego onControl: 1) $0008)
					(ego
						loop: 2
						illegalBits: -32768
						ignoreActors: 0
						setMotion: MoveTo (ego x?) (+ (ego y?) (ego yStep?)) self
					)
					(-- global110)
				else
					(if (and (FirstEntry) msgID) (Print msgID))
					(HandsOn)
					(= global109 (& global109 $ffef))
					(= global110 -1)
					(self cue:)
				)
			)
			(6
				(HandsOff)
				(= global109 (| global109 $0010))
				(self gateFunc: 1 -1)
				(ego
					setMotion: MoveTo elevX (+ gateY (ego yStep?))
					init:
				)
			)
			(7
				(ego
					ignoreActors: 1
					setMotion: MoveTo elevX (- elevY 2) self
				)
			)
			(8
				(HandsOn)
				(ego hide:)
				(elevatorID setLoop: 1 forceUpd:)
				(self gateFunc: 0 -1)
			)
			(9
				(ego
					posn: elevX (- elevY 2)
					illegalBits: 0
					ignoreActors: 1
				)
				(= global110 -1)
				(self cue:)
			)
			(10
				(cSound number: 66 prevSignal: 0 loop: 1 play:)
				(= global111 (| upRoomNo $8000))
				(HandsOff)
				(elevatorID cycleSpeed: 2 setCycle: End self)
			)
			(11
				(elevatorID
					setLoop: 1
					moveSpeed: 1
					setMotion: MoveTo elevX (- elevY 50) self
					startUpd:
				)
			)
			(12 (curRoom newRoom: upRoomNo))
			(13
				(cSound number: 66 prevSignal: 0 loop: 1 play:)
				(= global111 (| downRoomNo $8000))
				(HandsOff)
				(elevatorID cycleSpeed: 2 setCycle: End self)
			)
			(14
				(chainID
					view: 242
					setLoop: 3
					ignoreActors: 1
					illegalBits: 0
					posn: elevX chainY
					setPri: 6
					moveSpeed: 1
					setMotion: MoveTo elevX (+ chainY 52)
					init:
				)
				(elevatorID
					setLoop: 1
					moveSpeed: 1
					setMotion: MoveTo elevX (+ elevY 52) self
					startUpd:
				)
			)
			(15
				(curRoom newRoom: downRoomNo)
			)
			(16
				(cond 
					(
						(or
							(and (== curRoomNum 75) (== global111 42))
							(and (== curRoomNum 42) (== global111 32))
						)
						(self cue:)
					)
					((== curRoomNum 32) (ego view: 5 setCel: -1 posn: 297 125 setCycle: End self))
					((== curRoomNum 75) (curRoom newRoom: 42))
					(else (curRoom newRoom: 32))
				)
			)
			(17
				(cSound number: 90 loop: 1 play:)
				(ShakeScreen 10 5)
				(= cIcon 5)
				(= deathLoop 4)
				(= deathCel (ego lastCel:))
				(ego dispose:)
				(EgoDead 201 14)
			)
			(18
				(HandsOff)
				(cSound number: 89 loop: -1 play:)
				(ego
					view: 5
					setLoop: 4
					setCel: 0
					illegalBits: 0
					ignoreActors: 1
					posn: 297 125
					setPri: 6
					moveSpeed: 1
					setMotion: MoveTo 297 182
				)
				(elevatorID
					setLoop: 1
					posn: elevX (- (ego y?) 57)
					startUpd:
				)
				(elevatorID
					cel: (elevatorID lastCel:)
					moveSpeed: 1
					setMotion: MoveTo elevX elevY self
				)
				(chainID
					view: 5
					loop: 6
					cel: 0
					illegalBits: 0
					ignoreActors: 1
					posn: 295 126
					priority: 8
					cycleSpeed: 7
					setCycle: End
					init:
				)
			)
			(19
				(cSound stop:)
				(= cIcon 5)
				(= deathLoop 6)
				(= deathCel (chainID lastCel:))
				(ego dispose:)
				(EgoDead 201 15)
			)
		)
	)
	
	(method (gateFunc param1 param2)
		(HandsOff)
		(self startUpd:)
		(if (== param1 1)
			(cSound number: 81)
			(self setCycle: End self)
			(= global109 (| global109 gateStMask))
		else
			(cSound number: 79)
			(self setCycle: Beg self)
			(= global109 (& global109 (~ gateStMask)))
		)
		(cSound loop: 1 play:)
		(if
		(and (== curRoomNum 32) (!= curRoomNum global111))
			(elevatorID cel: param1 forceUpd:)
			(if
			(and (& (ego onControl: 1) $0020) (== param1 0))
				(= global110 17)
			)
		)
		(if (!= param2 -1) (ego loop: param2))
	)
	
	(method (elevatorFunc)
		(cond 
			((& global109 gateStMask) (AlreadyOpen))
			((& (ego onControl: 1) $0008)
				(if (!= curRoomNum global111)
					(self gateFunc: 1 3)
				else
					(= global109 (| global109 $0040))
					(= global110 5)
					(self cue:)
				)
			)
			((& (ego onControl: 1) $0020) (self gateFunc: 1 2))
			((& global109 $0010)
				(= global109 (| global109 $0040))
				(= global110 1)
				(self cue:)
			)
			(else (NotClose))
		)
	)
)
