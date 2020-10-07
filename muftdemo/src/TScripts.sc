;;; Sierra Script 1.0 - (do not remove this comment)
(script# TALES_SCRIPTS)
(include game.sh)
(use Main)
(use Procs)
(use Tactor)
(use PChase)
(use CDActor)
(use IconBar)
(use PolyPath)
(use MoveFwd)
(use StopWalk)
(use Smooper)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	goGet 0
	donkLooper 1
)

(local
	local0
	local1
	local2
	local3
	dogJumpPath = [
		1 8 2 -32
		0 2 3 4
		0 16 3 3
		4 0 -16 1
		8 2 -32 0
		0 8 2 32
		0 3 3 4
		0 -16 2 3
		4 0 16 0
		8 2 32
		]
	local44
	theInvItem
	theClient
	curRoomCx
	curRoomStartAngle
	local49
	local50
	local51
)
(class InvView of View
	(properties
		client 0
	)
	
	(method (init)
		(super init:)
		(= signal (& signal $feff))
	)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			(local44
				(if (>= (ego distanceTo: self) 45)
					(= local44 0)
				)
			)
			((<= (ego distanceTo: self) 35)
				(curRoom setScript: goGet 0 client)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(if (curRoom script?)
					0
				else
					(curRoom setScript: goGet 0 client)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class InvPossesion of InvItem
	(properties
		regView 0
	)
	
	(method (init)
		(super init:)
		(regView ignoreActors: FALSE init:)
	)
	
	(method (dispose)
		(inventory delete: self)
		(theIconBar curInvIcon: (EgoHas InvPossesion) show:)
	)
	
	(method (posn theX theY)
		(regView posn: theX theY)
	)
	
	(method (regViewDisp)
		(regView dispose:)
	)
	
	(method (setRegView theView)
		(self regView: theView)
		(regView client: self)
	)
)

(class InvActor of Tactor
	
	(method (init)
		(super init:)
		(self setCycle: Walk)
	)
)

(class InvFriend of InvItem
	(properties
		regActor 0
	)
	
	(method (init)
		(super init:)
		(regActor ignoreActors: 0 init:)
	)
	
	(method (dispose)
		(inventory delete: self)
	)
	
	(method (posn theX theY)
		(regActor posn: theX theY)
	)
	
	(method (regActorDisp)
		(regActor dispose:)
	)
	
	(method (setRegActor param1)
		(self regActor: param1)
		(regActor client: self)
	)
)

(class FRoom of Room
	(properties
		invX 0
		invY 0
		friendX 0
		friendY 0
		northX 0
		northY 0
		southX 0
		southY 0
		eastX 0
		eastY 0
		westX 0
		westY 0
		enterWithFriend TRUE
		defeatEntrance 0
	)
	
	(method (init &tmp temp0 temp1)
		(self enter:)
		(super init:)
		(DrawIconScroll)
		(if (= temp0 (InRoom InvPossesion))
			(temp0 posn: (curRoom invX?) (curRoom invY?) init:)
			((temp0 regView?) ignoreActors: 1)
		)
	)
	
	(method (doit)
		(if script
			(script doit:)
		else
			(super doit:)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (and (== theVerb verbLook) lookStr)
				(LowPrint lookStr)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (reflectPosn)
		(if (not defeatEntrance)
			(super reflectPosn: &rest)
		)
	)
	
	(method (enter)
		(if (not defeatEntrance)
			(= theClient (EgoHas InvFriend))
			(if (Btst 4)
				(self setScript: enterRM self 0)
				(Bclr 4)
			else
				(self
					setScript: enterRM self (self roomToEdge: prevRoomNum)
				)
				(Bclr 4)
			)
		else
			(self enterSpecial:)
			(Bclr 4)
		)
	)
	
	(method (enterSpecial)
	)
)

(class HandsOffScript of Script
	
	(method (init)
		(HandsOff)
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
)

(instance goGet of HandsOffScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local44 1)
				(= cycles 1)
			)
			(1
				(if (== curRoomNum 290)
					(self setScript: roseGet self register)
				else
					(self setScript: genericGet self register)
				)
			)
			(2
				(theIconBar curInvIcon: (EgoHas InvPossesion) show:)
				(= cycles 1)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance roseGet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 277 62 self)
			)
			(1
				(global230
					number: 113
					setLoop: 1
					flags: mNOPAUSE
					play:
				)
				(if (= theInvItem (EgoHas InvPossesion))
					(theInvItem moveTo: curRoomNum)
					(theInvItem posn: (curRoom invX?) (curRoom invY?) init:)
				)
				(register moveTo: ego)
				(register regViewDisp:)
				(= cycles 1)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance genericGet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theInvItem (InRoom InvPossesion))
				((theInvItem regView?) ignoreActors: TRUE)
				(if (> (ego x?) (curRoom invX?))
					(= local51 10)
				else
					(= local51 -10)
				)
				(ego
					setMotion: PolyPath (+ (curRoom invX?) local51) (curRoom invY?) self
				)
			)
			(1
				(ego
					view: (+ 70 egoView)
					cel: 0
					loop: (< (ego x?) (curRoom invX?))
					normal: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(global230
					number: 113
					setLoop: 1
					flags: mNOPAUSE
					play:
				)
				(if (= theInvItem (EgoHas InvPossesion))
					(theInvItem moveTo: curRoomNum)
					(theInvItem posn: (curRoom invX?) (curRoom invY?) init:)
					((theInvItem regView?) ignoreActors: 0)
				)
				(register moveTo: ego)
				(register regViewDisp:)
				(= cycles 1)
			)
			(3
				(ego setCycle: EndLoop self)
			)
			(4
				(ego normal: TRUE)
				(NormalEgo 5 egoView)
				(= cycles 1)
			)
			(5
				(self dispose:)
			)
		)
	)
)

(instance enterRM of HandsOffScript
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch register
					(1
						(= curRoomCx (curRoom northX?))
						(= curRoomStartAngle
							(if (curRoom horizon?)
								(curRoom horizon?)
							else
								(curRoom northY?)
							)
						)
						(ego setHeading: 180)
						(= local50 5)
					)
					(3
						(= curRoomCx (curRoom southX?))
						(= curRoomStartAngle (curRoom southY?))
						(ego setHeading: 0)
						(= local50 20)
					)
					(2
						(= curRoomCx (curRoom eastX?))
						(= curRoomStartAngle (curRoom eastY?))
						(ego setHeading: 270)
						(= local50 50)
					)
					(4
						(= curRoomCx (curRoom westX?))
						(= curRoomStartAngle (curRoom westY?))
						(ego setHeading: 90)
						(= local50 50)
					)
					(0
						(= curRoomCx 160)
						(= curRoomStartAngle 160)
					)
				)
				(NormalEgo 5 egoView)
				(= cycles 1)
			)
			(1
				(ego
					posn: curRoomCx curRoomStartAngle
					normal: 1
					init:
					setMotion: MoveFwd local50 self
				)
				(cast delete: ego addToFront: ego)
			)
			(2
				(if (not theClient)
					(self changeState: (= state (+ state 2)))
				else
					(switch register
						(1
							(theClient
								posn: curRoomCx (- curRoomStartAngle 20)
								init:
							)
							(= temp0 1)
							((theClient regActor?) setHeading: 180)
						)
						(3
							(theClient
								posn: curRoomCx (+ curRoomStartAngle 20)
								init:
							)
							(= temp0 5)
							((theClient regActor?) setHeading: 0)
						)
						(2
							(theClient
								posn: (+ curRoomCx 20) curRoomStartAngle
								init:
							)
							(= temp0 35)
							((theClient regActor?) setHeading: 270)
						)
						(4
							(theClient
								posn: (- curRoomCx 20) curRoomStartAngle
								init:
							)
							(= temp0 35)
							((theClient regActor?) setHeading: 90)
						)
						(0
							(theClient posn: 170 160 init:)
						)
					)
					((theClient regActor?)
						setCycle: StopWalk
						setMotion: MoveFwd temp0 self
					)
					(if ((theClient regActor?) isKindOf: CDActor)
						(cast delete: ((theClient regActor?) head?))
					)
					(cast delete: (theClient regActor?))
					(cast delete: ego)
					(cast delete: (ego head?))
					(if ((theClient regActor?) isKindOf: CDActor)
						(cast addToFront: ((theClient regActor?) head?))
					)
					(cast addToFront: (theClient regActor?))
					(cast addToFront: (ego head?))
					(cast addToFront: ego)
				)
			)
			(3
				(cond 
					((== (theBremenStory state?) 5) ((theClient regActor?) setMotion: NPFolDog ego 40))
					((== (theBremenStory state?) 1)
						((theClient regActor?)
							lookStr: {You see a rooster.}
							description: {Rooster}
							setMotion: NPFolRoos ego 40
						)
					)
					((== (theBremenStory state?) 7)
						((theClient regActor?)
							setLoop: donkLooper
							setMotion: NPFollow ego 40
						)
					)
					(else ((theClient regActor?) setMotion: NPFollow ego 40))
				)
				(= cycles 1)
			)
			(4 (self dispose:))
		)
	)
)

(class CodeIcon of IconItem
	(properties
		signal (| FIXED_POSN RELVERIFY)
		highlightColor 255
		lowlightColor 0
		value 0
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(if (not (& signal IB_ACTIVE)) (return))
		(= sColor
			(if (and argc tOrF) highlightColor else lowlightColor)
		)
		(= t nsTop)
		(= l nsLeft)
		(= b (- nsBottom 1))
		(= r (- nsRight 1))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l sColor -1 -1)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
	)
	
	(method (mask)
		(DrawCel maskView maskLoop maskCel nsLeft nsTop -1)
	)
)

(class TextIcon of IconItem
	(properties
		signal (| FIXED_POSN RELVERIFY)
		highlightColor 7
		lowlightColor 3
		value 0
		text 0
		textColor 50
	)
	
	(method (show)
		(super show: &rest)
		(DoDisplay text textColor (+ nsLeft 3) (+ nsTop 4))
	)
	
	(method (select relVer &tmp event whichCel nextCel)
		(= nextCel 1)
		(if (and argc relVer (& signal RELVERIFY))
			(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
			(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
			(DoDisplay
				text
				(- textColor 2)
				(+ nsLeft 3)
				(+ nsTop 4)
			)
			(while (!= ((= event (Event new:)) type?) mouseUp)
				(event localize:)
				(cond 
					((self onMe: event)
						(if (not whichCel)
							(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
							(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							(DoDisplay
								text
								(- textColor 2)
								(+ nsLeft 3)
								(+ nsTop 4)
							)
						)
					)
					(whichCel
						(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
						(DoDisplay text textColor (+ nsLeft 3) (+ nsTop 4))
					)
				)
				(event dispose:)
			)
			(event dispose:)
			(if (== whichCel 1)
				(DrawCel view loop 0 nsLeft nsTop -1)
				(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
				(DoDisplay text textColor (+ nsLeft 3) (+ nsTop 4))
			)
			(= nextCel whichCel)
		)
		(return nextCel)
	)
)

(class NPFollow of PFollow
	(properties
		hDist 0
		vDist 0
		initDist 0
	)
	
	(method (init)
		(super init: &rest)
		(if (== initDist 0)
			(= initDist distance)
		)
		(= vDist (/ (* initDist 2) 3))
		(= hDist initDist)
	)
	
	(method (doit &tmp egoHeading)
		(if
			(or
				(>= (= egoHeading (ego heading?)) 315)
				(<= egoHeading 45)
				(and (>= egoHeading 135) (<= egoHeading 225))
			)
			(= distance vDist)
		else
			(= distance hDist)
		)
		(super doit: &rest)
	)
)

(class NPFolDog of NPFollow
	
	(method (init)
		(super init: &rest)
		(= local2 (Random 60 100))
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (ego mover?))
				(not (client script?))
				(!= local1 (= local0 (GetTime SYSTIME1)))
			)
			(= local0 local1)
			(if (not (-- local2))
				(= local2 (Random 40 70))
				(if (<= (ego distanceTo: client) 40)
					(client setScript: dogJump 0 client)
				)
			)
		)
	)
)

(instance dogJump of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local3
					(/
						(GetAngle
							(ego x?)
							(ego y?)
							(register x?)
							(register y?)
						)
						45
					)
				)
				(register
					view: 648
					setLoop: [dogJumpPath (+ (* local3 5) 0)]
					setCel: 0
					xStep: [dogJumpPath (+ (* local3 5) 1)]
					yStep: [dogJumpPath (+ (* local3 5) 2)]
					normal: 0
					setCycle: Forward
					setMotion:
						PolyPath
						(+ (register x?) [dogJumpPath (+ (* local3 5) 3)])
						(+ (register y?) [dogJumpPath (+ (* local3 5) 4)])
						self
				)
			)
			(1
				(register
					view: 647
					cycleSpeed: 8
					moveSpeed: 8
					setLoop: -1
					normal: 1
					setCycle: StopWalk
					setMotion: NPFolDog ego 40
				)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(class NPFolRoos of NPFollow
	
	(method (init)
		(super init: &rest)
		(= local2 (Random 60 100))
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (ego mover?))
				(not (curRoom script?))
				(!= local1 (= local0 (GetTime SYSTIME1)))
			)
			(= local0 local1)
			(if (not (-- local2))
				(= local2 (Random 40 70))
				(if (<= (ego distanceTo: client) 40)
					(curRoom setScript: roosPeck 0 client)
				)
			)
		)
	)
)

(instance roosPeck of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(register
					setCel: 0
					setLoop: (Random 4 7)
					setCycle: EndLoop self
				)
			)
			(1
				(register
					setLoop: -1
					setCycle: StopWalk
					setMotion: NPFolRoos ego 40
				)
				(= ticks 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance donkLooper of SmoothLooper
	(properties
		cycleSpeed 6
		vChangeDir 633
	)
	
	(method (init)
		(super init: &rest)
		(= inProgress (= oldCycler (= oldMover 0)))
	)
	
	(method (doit)
		(super doit: &rest)
		(if (= theClient (EgoHas InvFriend))
			(= theClient (theClient regActor?))
		else
			(= theClient client)
		)
		(cond 
			((and inProgress (theClient normal?))
				(theClient normal: FALSE)
			)
			((and (not inProgress) (not (theClient normal?)))
				(theClient normal: TRUE)
			)
		)
	)
	
	(method (cue)
		(if (< nextLoop 15)
			(client normal: TRUE)
		)
		(super cue: &rest)
	)
)
