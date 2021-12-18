;;; Sierra Script 1.0 - (do not remove this comment)
(script# 952)
(include sci.sh)
(use Main)
(use Procs)
(use Sound)
(use Game)
(use System)

(public
	soundRoom 0
)

(local
	[local0 30]
	local30 =  -1
	local31 =  -1
	local32
	local33
	theGCycleCnt
	local35
	local36 =  127
	local37
)
(instance soundRoom of Room
	(properties)
	
	(method (init)
		(super init: &rest)
	)
)

(class ShiversSound of Sound
	(properties
		scratch 0
		nodePtr 0
		handle 0
		flags $0000
		number 0
		vol 127
		priority 0
		loop 1
		signal $0000
		prevSignal 0
		dataInc 0
		min 0
		sec 0
		frame 0
		client 0
		owner 0
		channelNum 0
		script 0
	)
	
	(method (init)
		(theDoits add: self)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
		(super doit: &rest)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose: &rest)
	)
	
	(method (pause param1)
		(if (== param1 1) (= local37 1) else (= local37 0))
		(super pause: param1 &rest)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(if newScript (newScript init: self))
	)
)

(class SoundManager of Sounds
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (play param1 param2 param3 param4 &tmp soundManagerGetFreeSound soundManagerFirst temp2)
		(return
			(if
				(==
					(= soundManagerGetFreeSound
						(self isAlreadyPlaying: param1)
					)
					0
				)
				(if
				(!= (= soundManagerGetFreeSound (self getFreeSound:)) 0)
					(proc951_7 param1)
					(soundManagerGetFreeSound number: param1)
					(soundManagerGetFreeSound setLoop: param2)
					(soundManagerGetFreeSound play: param3 param4)
				else
					(MonoOut {Ran out of channels!!!})
					(= soundManagerFirst (self first:))
					(= temp2 0)
					(while soundManagerFirst
						(if
							(and
								(or
									(==
										((= soundManagerGetFreeSound (KList 8 soundManagerFirst))
											vol?
										)
										0
									)
									(== (soundManagerGetFreeSound handle?) 0)
								)
								(!= (soundManagerGetFreeSound script?) 0)
							)
							(soundManagerGetFreeSound setVol: 0)
							(soundManagerGetFreeSound stop:)
							(soundManagerGetFreeSound client: 0)
							(soundManagerGetFreeSound number: 0)
						)
						(= soundManagerFirst (self next: soundManagerFirst))
						(++ temp2)
					)
					(if (!= param4 0) (param4 cue:))
				)
				(return soundManagerGetFreeSound)
			else
				(MonoOut
					{Sound %d already playing!! Resume.}
					param1
					param3
				)
				(if (== (soundManagerGetFreeSound vol?) 0)
					(soundManagerGetFreeSound stop:)
				)
				(proc951_7 param1)
				(soundManagerGetFreeSound number: param1)
				(soundManagerGetFreeSound setLoop: param2)
				(soundManagerGetFreeSound play: param3 param4)
				(return soundManagerGetFreeSound)
			)
		)
	)
	
	(method (stop param1 &tmp soundManagerFirst temp1 temp2)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(if
				(==
					((= temp1 (KList 8 soundManagerFirst)) number?)
					param1
				)
				(= temp2 (temp1 number?))
				(temp1 setVol: 0)
				(temp1 stop:)
				(temp1 client: 0)
				(temp1 number: 0)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
		(proc951_10 temp2)
		(proc951_8 temp2)
	)
	
	(method (fade param1 param2 param3 param4 param5 param6 &tmp soundManagerFirst temp1 temp2 temp3 temp4)
		(= temp2 0)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(if
				(and
					(==
						((= temp1 (KList 8 soundManagerFirst)) number?)
						param1
					)
					(!= (temp1 handle?) 0)
				)
				(= temp2 1)
				(cond 
					(
					(> (= temp4 (Abs (- (temp1 vol?) param2))) 80) (= temp3 2))
					((< temp4 40) (= temp3 4))
					(else (= temp3 3))
				)
				(MonoOut
					{Fading %d from %d to %d,handle#%d}
					(temp1 number?)
					(temp1 vol?)
					param2
					(temp1 handle?)
				)
				(if (== temp4 0)
					(temp1 fade: param2 temp3 10 param5 param6)
					(break)
				)
				(temp1 fade: param2 temp3 temp4 param5 param6)
				(break)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
		(if (and (== temp2 0) (!= param6 0))
			(MonoOut {%d Fade not present!!! Cueing} param1)
			(param6 cue:)
		)
	)
	
	(method (setVol param1 param2 &tmp soundManagerFirst temp1)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(if
				(==
					((= temp1 (KList 8 soundManagerFirst)) number?)
					param1
				)
				(temp1 setVol: param2)
				(break)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
	)
	
	(method (getFreeSound &tmp soundManagerFirst temp1)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(if
				(and
					(== ((= temp1 (KList 8 soundManagerFirst)) handle?) 0)
					(== (temp1 script?) 0)
				)
				(MonoOut {channel %d} (temp1 channelNum?))
				(break)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
		(return (if soundManagerFirst temp1 else 0))
	)
	
	(method (playChain param1 &tmp soundsGetFreeSound temp1 [temp2 2])
		(if (mod argc 2)
			(MonoOut {Failed chain creation, argument problem})
			(return 0)
		)
		(return
			(if (>= local31 0)
				(MonoOut {Adding sounds to chain})
				(if (== local31 (mod (+ local30 argc) 30))
					(MonoOut {Failed add chain creation, state problem})
					(return 0)
				)
				(= temp1 0)
				(while (< temp1 argc)
					(= [local0 local30] [param1 temp1])
					(= [local0 (+ local30 1)] [param1 (+ temp1 1)])
					(= local30 (mod (+ local30 2) 30))
					(= temp1 (+ temp1 2))
				)
			else
				(MonoOut {Creating new chain})
				(= local31 0)
				(= local30 0)
				(if (== local31 (mod (+ local30 argc) 30))
					(MonoOut {Failed new chain creation, state problem})
					(return 0)
				)
				(= temp1 0)
				(while (< temp1 argc)
					(= [local0 local30] [param1 temp1])
					(MonoOut
						{chain: %d %d}
						[local0 local30]
						[local0 (+ (= [local0 (+ local30 1)] [param1 (+ temp1 1)]) 1)]
					)
					(= local30 (mod (+ local30 2) 30))
					(= temp1 (+ temp1 2))
				)
				(if
				(!= (= soundsGetFreeSound (sounds getFreeSound:)) 0)
					(soundsGetFreeSound setScript: sChain)
				)
			)
		)
	)
	
	(method (stopChain &tmp temp0)
		(= local33 0)
		(= local37 0)
		(= local36 0)
		(= [local0 (= temp0 (mod (+ local31 2) 30))] 0)
		(= [local0 (+ local31 1)] 0)
	)
	
	(method (interruptChain &tmp temp0)
		(= local33 0)
		(= local37 0)
		(sChain state: 1 cue:)
	)
	
	(method (fadeChain &tmp temp0)
		(= local33 0)
		(= local37 0)
		(sChain state: 0 cue:)
	)
	
	(method (adjChainVol param1)
		(= local36 param1)
	)
	
	(method (stopAll &tmp soundManagerFirst temp1 temp2)
		(= local37 0)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(= temp1 (KList 8 soundManagerFirst))
			(if (== 0 (temp1 script?))
				(if (> (= temp2 (temp1 number?)) 0)
					(temp1 setVol: 0)
					(temp1 stop:)
					(temp1 client: 0)
					(temp1 number: 0)
					(proc951_10 temp2)
					(proc951_8 temp2)
				)
			else
				(= local36 0)
				(sounds interruptChain:)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
	)
	
	(method (isAlreadyPlaying param1 &tmp soundManagerFirst temp1)
		(= soundManagerFirst (self first:))
		(while soundManagerFirst
			(if
				(and
					(==
						((= temp1 (KList 8 soundManagerFirst)) number?)
						param1
					)
					(!= (temp1 handle?) 0)
				)
				(return temp1)
			)
			(= soundManagerFirst (self next: soundManagerFirst))
		)
		(return 0)
	)
)

(instance sChain of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(if (== [local0 local31] -1)
					(= temp1 (Abs [local0 (+ local31 1)]))
					(MonoOut {pausing %d seconds} temp1)
					(= theGCycleCnt gameTime)
					(= local35 (* 60 temp1))
					(= local33 1)
					(if (< [local0 (+ local31 1)] 0)
						(= [local0 local30] [local0 local31])
						(= [local0 (+ local30 1)] [local0 (+ local31 1)])
						(= local30 (mod (+ local30 2) 30))
						(= [local0 local31] 0)
						(= [local0 (+ local31 1)] 0)
					)
					(= local31 (mod (+ local31 2) 30))
				)
				(cond 
					((or (== local33 1) (== local37 1))
						(if (> (- gameTime theGCycleCnt) local35)
							(= local33 0)
						)
						(= state -1)
						(= cycles 1)
					)
					((> [local0 local31] 5000)
						(if (and (!= [local0 local31] local32) local32)
							(client setVol: 0)
							(client stop:)
							(client number: 0)
							(proc951_10 local32)
							(proc951_8 local32)
						)
						(= local32 [local0 local31])
						(= temp0 (mod (+ local31 2) 30))
						(if (== [local0 (+ local31 1)] -1)
							(= [local0 local30] [local0 local31])
							(= [local0 (+ local30 1)] -1)
							(= local30 (mod (+ local30 2) 30))
						)
						(= [local0 local31] 0)
						(= [local0 (+ local31 1)] 0)
						(= local31 temp0)
						(proc951_7 local32)
						(proc951_9 local32)
						(MonoOut {chain playing %d} local32)
						(client number: local32 setLoop: 0 play: local36 self)
						(= state -1)
					)
					(else (= state 1) (self cue:))
				)
			)
			(1
				(if local32
					(MonoOut {chain fade from %d to 0} local36)
					(if local36
						(client fade: 0 5 60 1 self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(MonoOut {chain stopped})
				(if local32
					(client setVol: 0)
					(client stop:)
					(client number: 0)
					(proc951_10 local32)
					(proc951_8 local32)
				)
				(= temp2 0)
				(while (< temp2 30)
					(= [local0 temp2] 0)
					(= temp2 (+ temp2 1))
				)
				(= local33 0)
				(= local32 0)
				(= local30 -1)
				(= local31 -1)
				(= local36 127)
				(= local37 0)
				(self dispose:)
			)
		)
	)
)
