;;; Sierra Script 1.0 - (do not remove this comment)
(script# BEANS)
(include game.sh)
(use Main)
(use Intrface)
(use StopWalk)
(use LoadMany)
(use Reverse)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)


(public
	beanRg 0
)

(local
	local0
	local1
	local2
)
(instance beanRg of Region
	(properties)
	
	(method (init)
		(self keep: FALSE)
		(super init: &rest)
		(LoadMany VIEW 1 270)
		(Load PICTURE 270)
		(Load SOUND 94)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event))
			((Said 'bury/bean')
				(cond 
					(stalkRoom
						(Print 606 0)
					)
					((not (ego has: iBeans))
						(if (Btst fAteBeans)
							(Print 606 1)
						else
							(DontHave)
						)
					)
					((not (& (ego onControl: origin) cLGREEN))
						(Print 606 2)
					)
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(Print 606 3)
					)
					(else
						(if
							(and
								(cast contains: theGoat)
								(theGoat inRect: 110 125 220 150)
							)
							(= local1 (if (< (theGoat x?) 165) -1 else 1))
							(= local2 (if (< (theGoat y?) 137) -1 else 1))
						)
						(curRoom setScript: plantBeans)
					)
				)
			)
		)
	)
)

(instance plantBeans of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(> state 3)
				(!= local0 ((ScriptID 0 21) prevSignal?))
			)
			(= local0 ((ScriptID 0 21) prevSignal?))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 131 138 self
				)
			)
			(1
				(SolvePuzzle fPlantedBeanstalk 2)
				(ego
					loop: 0
					cel: 0
					view: 1
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(NormalEgo)
				(Print 606 4)
				(self cue:)
			)
			(4
				((ScriptID 0 21) number: 94 init: play:)
				(ego setLoop: 0 setCycle: SyncWalkBack 2)
				(self cue:)
			)
			(5 0)
			(6
				(ShakeScreen 1 shakeSDown)
				(stalk posn: 149 138 init: setCycle: EndLoop)
				(ego setMotion: MoveTo 127 138)
				(if local1
					(= deadGoatX (+ (theGoat x?) (* local1 5)))
					(= deadGoatY (+ (theGoat y?) (* local2 3)))
					(theGoat setMotion: MoveTo deadGoatX deadGoatY)
				)
				(= stalkRoom curRoomNum)
			)
			(7
				(ShakeScreen 8 shakeSDiagonal)
				(stalk loop: 1 cel: 0 setCycle: EndLoop)
				(ego setMotion: MoveTo 117 138)
				(if local1
					(= deadGoatX (+ (theGoat x?) (* local1 5)))
					(= deadGoatY (+ (theGoat y?) (* local2 3)))
					(theGoat setMotion: MoveTo deadGoatX deadGoatY)
				)
			)
			(8
				(ShakeScreen 12 shakeSDown)
				(stalk loop: 2 cel: 0 setCycle: CycleTo 5 1)
				(ego setMotion: MoveTo 107 138)
				(if local1
					(= deadGoatX (+ (theGoat x?) (* local1 5)))
					(= deadGoatY (+ (theGoat y?) (* local2 3)))
					(theGoat setMotion: MoveTo deadGoatX deadGoatY)
				)
			)
			(9
				(ShakeScreen 18 shakeSDiagonal)
				(if local1
					(= deadGoatX (+ (theGoat x?) (* local1 5)))
					(= deadGoatY (+ (theGoat y?) (* local2 3)))
					(theGoat setMotion: MoveTo deadGoatX deadGoatY)
				)
				(stalk stopUpd:)
				(self cue:)
			)
			(10
				(curRoom overlay: 270 WIPEUP)
				(NormalEgo)
				(Face ego stalk)
				(stalk dispose:)
				(if
				(and (!= deadGoatRoom curRoomNum) (Btst fGoatFollows))
					(= deadGoatX 0)
					(= deadGoatY 0)
					(theGoat setMotion: Follow ego 60)
				)
				(HandsOn)
				(curRoom setRegions: 607)
				(self dispose:)
			)
		)
	)
)

(instance stalk of Prop
	(properties
		description {beanstalk}
		view 270
		signal $0000
		cycleSpeed 2
	)
)

(class SyncWalkBack of StopWalk
	;was a subclass of Reverse, but this appears to be a decompilation error.
	;The fact that it has vWalking and vStopped properties shows that it's actually
	;a subclass of StopWalk.
	(properties
		xLast 0
		yLast 0
	)
	
	(method (init who stopView)
		(if argc
			(= vWalking ((= client who) view?))
			(if (>= argc 2) (= vStopped stopView))
		)
		(super init: client)
	)
	
	(method (doit &tmp mv mv2 temp2)
		(if
			(and
				(IsObject (= mv (client mover?)))
				(or (!= (client x?) xLast) (!= (client y?) yLast))
			)
			(= xLast (client x?))
			(= yLast (client y?))
			(super doit:)
		)
		(if (client isStopped:)
			(if (== (client view?) vWalking)
				(client view: vStopped)
				(if
					(and
						(= mv2 (client mover?))
						(not (mv2 completed?))
					)
					(client setMotion: 0)
				)
				(super doit:)
			)
		else
			(if (== (client view?) vStopped)
				(client view: vWalking)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(if (== (client view?) vStopped)
			(client view: vWalking)
		)
		(super dispose:)
	)
	
	(method (nextCel)
		(= cycleCnt (client cycleSpeed?))
		(super nextCel:)
	)
)
