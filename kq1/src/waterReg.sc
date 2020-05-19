;;; Sierra Script 1.0 - (do not remove this comment)
(script# WATER)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	waterReg 0
)

(local
	waterControl
	local1
	oldEgoLoop
	local3
)
(instance waterReg of Region
	(properties)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 10 11 12 13 14)
		(if (== egoInWater 4)
			(= local3 1)
		)
		(self doit:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(OneOf (ego onControl: origin) cBLUE cGREEN cCYAN cRED)
			)
			(curRoom setScript: water)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'swim<cease')
				(switch (ego view?)
					(13
						(Print 603 0)
					)
					(else
						(Print 603 1)
					)
				)
			)
			(
			(or (Said 'swim/') (Said 'enter,go/water,lake,lake'))
				(cond 
					((and (== (curRoom script?) drowning) (>= (drowning state?) 3))
						(Print 603 2)
					)
					((== egoInWater 4)
						(Print 603 3)
					)
					(
						(or
							(& (ego onControl: origin) cRED)
							(& (ego onControl: origin) cCYAN)
						)
						(NormalEgo)
						(= local3 1)
						(= egoInWater 4)
						(= swimTimer 1000)
						(ego view: 13 setCycle: Forward)
						(curRoom setScript: water)
						(HandsOn)
					)
					(
						(or
							(& (ego onControl: origin) cBLUE)
							(& (ego onControl: origin) cGREEN)
						)
						(Print 603 4)
					)
					((== egoInWater 0)
						(Print 603 5)
					)
				)
			)
			((Said 'dive/')
				(cond 
					((== egoInWater 0)
						(Print 603 6)
					)
					(egoInWater
						(cond 
							((and (>= egoInWater 1) (<= egoInWater 3))
								(Print 603 7)
							)
							((& (ego onControl: origin) cCYAN)
								(Print 603 8)
							)
							(else
								(Print 603 9)
							)
						)
					)
				)
			)
			(
			(or (Said 'get,take/water') (Said 'fill/bucket'))
				(cond 
					((not egoInWater)
						(Print 603 10)
					)
					((ego has: iWaterBucket)
						(if (Btst fWaterInBucket)
							(Print 603 11)
						else
							(if (== egoInWater 4)
								(Print 603 12)
							else
								(Print 603 13)
							)
							(BucketState TRUE)
						)
					)
					(else
						(Print 603 14)
					)
				)
			)
		)
	)
)

(instance water of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(self doit:)
	)
	
	(method (doit)
		(if (and (Btst fInvisible) (!= (ego onControl: origin) cBLACK))
			(Print 603 15)
			(Bclr fInvisible)
			(NormalEgo)
		)
		(if
			(and
				(& (= waterControl (ego onControl: origin)) cCYAN cRED)
				(== egoInWater 4)
				swimTimer
				(not (-- swimTimer))
			)
			(curRoom setScript: drowning)
		)
		(if (!= waterControl local1)
			(= local1 waterControl)
			(cond 
				((& waterControl cBLUE)
					(if (== ((ScriptID 0 21) number?) 51)
						((ScriptID 0 21) stop:)
					)
					(= egoInWater 1)
					(= local3 0)
					(ego setCycle: Walk view: 0 cycleSpeed: 0 moveSpeed: 0)
					(splash ignoreActors: init:)
				)
				((& waterControl cGREEN)
					(if (== ((ScriptID 0 21) number?) 51)
						((ScriptID 0 21) stop:)
					)
					(= egoInWater 2)
					(splash dispose:)
					(ego setCycle: Walk view: 11 cycleSpeed: 1 moveSpeed: 1)
					(= local3 0)
				)
				((& waterControl cCYAN)
					(if (== ((ScriptID 0 21) number?) 51)
						((ScriptID 0 21) stop:)
					)
					(if (not local3)
						(ego setCycle: Walk view: 12 cycleSpeed: 2 moveSpeed: 2)
						(= oldEgoLoop (ego loop?))
						(= egoInWater 3)
					else
						(ego setCycle: Forward)
					)
				)
				((& waterControl cRED)
					(= egoInWater 4)
					(= swimTimer 1000)
					(ego view: 13 cycleSpeed: 0 moveSpeed: 0 setCycle: Forward)
					((ScriptID 0 21) number: 51 loop: -1 play:)
				)
				((and (!= 0 waterControl) (!= waterControl cYELLOW))
					(if (== ((ScriptID 0 21) number?) 51)
						((ScriptID 0 21) stop:)
					)
					(= egoInWater 0)
					(= local3 0)
					(splash dispose:)
					(ego setCycle: StopWalk 2)
					(self dispose:)
				)
			)
		)
	)
)

(instance drowning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(= oldEgoLoop (ego loop?))
				(ego
					setMotion: 0
					setLoop: 0
					view: 14
					cel: 5
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego cel: 5 setCycle: EndLoop self)
			)
			(2
				(ego cel: 5 setCycle: EndLoop self)
			)
			(3
				(ego hide:)
				(= seconds 4)
			)
			(4
				(if (not swimTimer)
					(EgoDead
						{After swimming for a long time, your strength ebbs and your arms and legs grow weary.__As your life swims before your eyes, you decide to...}
					)
				else
					(EgoDead
						{You splash around for awhile, but unfortunately that won't keep your head above water.__As you go down for the third time, a sense of peace washes over you...}
					)
				)
			)
		)
	)
)

(instance splash of Prop
	(properties
		view 10
		signal $0000
	)
	
	(method (doit)
		(self
			posn: (ego x?) (ego y?)
			cel: (ego cel?)
			loop: (ego loop?)
		)
		(super doit: &rest)
	)
	
	(method (doVerb)
	)
)
