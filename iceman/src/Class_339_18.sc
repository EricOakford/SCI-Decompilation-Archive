;;; Sierra Script 1.0 - (do not remove this comment)
(script# 339)
(include game.sh)
(use Main)
(use subMarine)
(use n330)
(use n331)
(use n332)
(use n333)
(use n334)
(use n335)
(use n396)
(use Submarine_806)
(use EgoDead)
(use ForCount)
(use Actor)
(use System)

(public
	battleShip1 0
	Class_339_16 1
)

(local
	[local0 12]
	[local12 12]
	local24
	local25
	local26
	local27 = [0 1 2 3 4 5]
	local33 = [0 1 2 3 4 5]
	local39 =  2400
	local40
	local41
	[local42 2]
	local44
	local45
	local46
	globalSoundNumber
	local48 =  3
	globalSoundClient
	local50 =  3
	local51 =  1
)
(procedure (localproc_06ca &tmp temp0 temp1)
	(if local41 (return 0))
	(= temp1 0)
	(= temp0 0)
	(while (< temp0 6)
		(if
			(and
				[local12 temp0]
				(<= (= [local12 temp0] (- [local12 temp0] local50)) 0)
			)
			(= [local12 temp0] 0)
			([local12 (+ temp0 6)] setCel: 10)
			(= [local12 (+ temp0 6)] 0)
			(= temp1 1)
		)
		(++ temp0)
	)
	(return temp1)
)

(procedure (localproc_0726 &tmp temp0 temp1)
	(= temp0 0)
	(while (< temp0 6)
		(if
			(and
				[local0 (+ temp0 6)]
				(!=
					([local0 (+ temp0 6)] cel?)
					(= temp1 (localproc_07d7 [local0 temp0]))
				)
			)
			([local0 (+ temp0 6)] setCel: temp1)
		)
		(++ temp0)
	)
	(= temp0 0)
	(while (< temp0 6)
		(if
			(and
				[local12 (+ temp0 6)]
				(!=
					([local12 (+ temp0 6)] cel?)
					(= temp1 (localproc_07d7 [local12 temp0]))
				)
			)
			([local12 (+ temp0 6)] setCel: temp1)
		)
		(++ temp0)
	)
	(if
		(and
			local24
			(!= (local24 cel?) (= temp1 (localproc_07d7 local39)))
		)
		(local24 setCel: temp1)
	)
)

(procedure (localproc_07d7 param1 &tmp temp0)
	(= temp0 (Abs (/ param1 100)))
	(return
		(if (or (== param1 0) (< 9 temp0))
			(return 10)
		else
			(return temp0)
		)
	)
)

(instance battleShip1 of Script
	
	(method (doit &tmp temp0)
		(= temp0 0)
		(while (< temp0 6)
			(if (< 0 [local0 temp0])
				(= [local0 temp0] (- [local0 temp0] local50))
				(if (== [local0 temp0] 0) (-- [local0 temp0]))
			)
			(++ temp0)
		)
		(if
			(and
				(localproc_06ca)
				(< (Random 0 (+ local39 250)) 250)
			)
			(++ local40)
			(target setCycle: ForwardCounter local48)
			(++ local48)
		)
		(if
			(not
				(if (or local41 script)
				else
					(= local39 (- local39 local51))
				)
			)
			(self setScript: (ScriptID 328 0))
		)
		(if local46 (-- local46))
		(localproc_0726)
		(super doit:)
	)
	
	(method (dispose)
		(cls)
		(DisposeScript 956)
		(DisposeScript 396)
		(if (< state 10)
			(EgoDead 926 1 0 339 0)
		else
			(super dispose:)
			(DisposeScript 339)
		)
	)
	
	(method (changeState newState &tmp temp0 theSeconds [temp2 15])
		(switch (= state newState)
			(0
				(switch howFast
					(0 (= local50 6) (= local51 3))
					(1 (= local50 4) (= local51 2))
				)
				(= globalSoundNumber (globalSound number?))
				(= globalSoundClient (globalSound client?))
				(= local24 (bar1 init: stopUpd: yourself:))
				(= [local27 0] (torpedo0 init: yourself:))
				(= [local27 1] (torpedo1 init: yourself:))
				(= [local27 2] (torpedo2 init: yourself:))
				(= [local27 3] (torpedo3 init: yourself:))
				(= [local27 4] (torpedo4 init: yourself:))
				(= [local27 5] (torpedo5 init: yourself:))
				(= [local33 0] (torpedo6 init: yourself:))
				(= [local33 1] (torpedo7 init: yourself:))
				(= [local33 2] (torpedo8 init: yourself:))
				(= [local33 3] (torpedo9 init: yourself:))
				(= [local33 4] (torpedo10 init: yourself:))
				(= [local33 5] (torpedo11 init: yourself:))
				(blackHawk init:)
				(target init:)
				(= seconds 2)
			)
			(1
				(= start state)
				(proc331_0 local39 1)
				(DisposeScript 331)
				(= seconds 3)
			)
			(2
				(if local44 (SubPrint 4 339 7) (-- local44))
				(if (< 2 local40)
					(SubPrint 4 339 8)
					(proc333_0)
					(DisposeScript 333)
					(local24 dispose:)
					(target hide: forceUpd: dispose: delete:)
					(= local24 0)
					(= local41 1)
					(= seconds 5)
				else
					(= cycles 2)
				)
			)
			(3
				(= seconds (proc332_0))
				(DisposeScript 332)
			)
			(4
				(= seconds (proc330_0))
				(DisposeScript 330)
			)
			(5
				(= temp0 0)
				(= theSeconds 1)
				(while (< temp0 6)
					(if (< [local0 temp0] 0)
						(cond 
							((< 5 (Submarine hSpeed?)) (self setScript: (ScriptID 329 0) 0 0))
							((< (Submarine depth:) 500) (self setScript: (ScriptID 329 0) 0 3))
							((& (Submarine flags?) $0080) (self setScript: (ScriptID 329 0) 0 1))
							((not (& (Submarine flags?) $0040)) (self setScript: (ScriptID 329 0) 0 2))
							(local46 (self setScript: (ScriptID 329 0) 0 4))
							(else
								(= [local0 temp0] 0)
								([local0 (+ temp0 6)] setCel: 10)
								(= [local0 (+ temp0 6)] 0)
								(= theSeconds 4)
								(switch (Random 0 2)
									(0 (SubPrint 3 339 9))
									(1 (SubPrint 3 339 10))
									(2
										(globalSound
											number: (SoundFX 20)
											loop: 1
											owner: theGame
											priority: 1
											play:
										)
										(ShakeScreen 30)
										(SubPrint 3 339 11)
									)
								)
							)
						)
					)
					(++ temp0)
				)
				(= seconds theSeconds)
			)
			(6
				(globalSound
					number: globalSoundNumber
					loop: -1
					owner: theGame
					priority: 1
					play: globalSoundClient
				)
				(if local45
					(-- local45)
					(if (proc335_0) (= seconds 4) else (= cycles 2))
					(DisposeScript 335)
				else
					(= cycles 2)
				)
			)
			(7
				(if (not local41)
					(Format @temp2 339 12 (* local39 7))
					(SubPrint 3 @temp2)
					(= seconds 4)
				else
					(= cycles 2)
				)
			)
			(8 (= seconds (Random 1 2)))
			(9
				(if (and local41 (not local25))
					(SubPrint 4 339 13)
					(blackHawk hide: forceUpd: dispose: delete:)
					(theGame changeScore: 5)
					(subMarine cue:)
					(= seconds 4)
				else
					(self init:)
				)
			)
			(10
				(SubPrint 4 339 14)
				(= seconds 45)
			)
			(11
				(self setScript: (ScriptID 327 0))
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((== (event type?) 1024)
				(if (and local41 (!= (event message?) 1))
					(SubPrint 5 339 1)
				else
					(switch (event message?)
						(0
							(if (event modifiers?)
								(if (/ local39 300)
									(SubPrint 5 339 2)
									(proc334_0 (/ local39 4))
									(DisposeScript 334)
									(= local46 100)
								else
									(SubPrint 5 339 3)
								)
							else
								(++ local44)
								(SubPrint 5 339 2)
							)
						)
						(1
							(SubPrint 5 339 4)
							(++ local45)
						)
						(2
							(if (event modifiers?)
								(SubPrint 5 339 5)
								(proc334_0 local39)
								(DisposeScript 334)
								(= local46 100)
							else
								(SubPrint 5 339 6)
								(proc331_0 300 0)
							)
						)
					)
				)
			)
		)
	)
)

(instance blackHawk of View
	(properties
		y 65
		x 125
		view 527
		loop 9
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance target of Prop
	(properties
		y 39
		x 190
		view 527
		loop 7
		priority 14
		signal $0010
	)
)

(instance bar1 of View
	(properties
		y 41
		x 195
		view 627
		loop 2
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo0 of View
	(properties
		y 43
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo1 of View
	(properties
		y 45
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo2 of View
	(properties
		y 47
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo3 of View
	(properties
		y 49
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo4 of View
	(properties
		y 51
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo5 of View
	(properties
		y 53
		x 195
		view 627
		loop 1
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo6 of View
	(properties
		y 66
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo7 of View
	(properties
		y 68
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo8 of View
	(properties
		y 70
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo9 of View
	(properties
		y 72
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo10 of View
	(properties
		y 74
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance torpedo11 of View
	(properties
		y 76
		x 119
		view 627
		cel 10
		priority 14
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(class Class_339_16
	(properties)
	
	(method (at param1 param2)
		(return
			(if (< 1 argc)
				(= [local0 param1] param2)
			else
				(return [local0 param1])
			)
		)
	)
)
