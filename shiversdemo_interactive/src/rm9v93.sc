;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9930)
(include sci.sh)
(use Main)
(use Procs)
(use ForCount)
(use Motion)
(use System)

(public
	rm9v93 0
)

(local
	local0
)
(instance rm9v93 of ShiversRoom
	(properties
		picture 9930
	)
	
	(method (init)
		(proc951_16 149)
		(spFaucet init:)
		(if (proc951_5 42)
			(efExitBack init: 8)
		else
			(efExitBack init: 8)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(super newRoom: n &rest)
		(if (!= n 9700) (sounds play: 10920 0 82 0))
	)
)

(instance efExitBack of ExitFeature
	(properties
		nextRoom 9390
	)
	
	(method (init)
		(self
			createPoly: 0 0 82 58 82 123 207 115 205 51 82 58 0 0 263 0 263 143 0 143
		)
		(super init: &rest)
	)
)

(instance spFaucet of ShiversProp
	(properties
		priority 11
		fixPriority 1
		view 9930
		cycleSpeed 15
	)
	
	(method (init)
		(self createPoly: 111 105 111 65 181 56 170 102)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (proc951_5 42)
			(self setScript: sFaucetOff)
			(proc951_4 42)
			(efExitBack nextRoom: 9390 cursorCel: 8)
		else
			(self setScript: sFaucetOn)
			(efExitBack nextRoom: 9700 cursorCel: 4)
			(proc951_3 42)
		)
	)
)

(instance sFaucetOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client setCycle: Beg self)
				(sounds stop: 10910)
				(sounds stop: 10908)
				(sounds play: 10910 -1 32 0)
				(= local0 4)
			)
			(1
				(client cel: (client lastCel:))
				(client setCycle: Beg self)
				(if local0 (-- local0) (-- state))
			)
			(2
				(sounds stop: 10910)
				(sounds stop: 20908)
				(sounds stop: 20909)
				(= cycles 1)
			)
			(3
				(proc951_9 10908)
				(sounds play: 10908 -1 0 0)
				(MonoOut {fade to 42})
				(sounds fade: 10908 42 15 8 0 0)
				(if (proc951_11 0 9000)
					(sounds fade: 20903 0 1 16 1 self)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(4
				(proc951_9 20908)
				(sounds play: 20908 -1 50 0)
				(proc951_9 20909)
				(sounds play: 20909 -1 34 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFaucetOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client setCycle: End self)
				(sounds stop: 10910)
				(sounds play: 10910 -1 32 0)
			)
			(1 (client cel: 0) (self cue:))
			(2
				(client setCycle: ForwardCounter 4 self)
			)
			(3
				(if (proc951_11 0 9000) (sounds stop: 20903))
				(client setCycle: End self)
			)
			(4
				(sounds stop: 10910)
				(MonoOut {fade to 0})
				(sounds fade: 10908 0 5 8 0 0)
				(if (proc951_11 0 9000)
					(sounds fade: 20908 0 1 16 1 self)
					(sounds fade: 20909 0 1 16 1 0)
				else
					(= cycles 1)
				)
			)
			(5
				(sounds stop: 20909)
				(= cycles 1)
			)
			(6
				(proc951_9 20903)
				(sounds play: 20903 -1 0 0)
				(sounds fade: 20903 50 10 16 0 0)
				(theGame handsOn:)
				(sounds stop: 10908)
				(self dispose:)
			)
		)
	)
)
