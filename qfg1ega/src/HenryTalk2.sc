;;; Sierra Script 1.0 - (do not remove this comment)
(script# 145)
(include game.sh)
(use Main)
(use TalkObj)
(use System)

(public
	sCave 0
	sErmit 1
	sFamily 2
	sEnry 3
	sCrib 4
	sTrig 5
	sSpell 6
	sLadder 7
	sFalls 8
)

(instance sCave of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 0)
				;"Well, it's dark and damp and the waters roars sos there's not a moment's quiet.
				;The ceiling drips and the walls are slimy and it's always cold.  But, oh well, sez I, it's 'ome."
			)
			(1
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sErmit of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 1)
				;"A 'ermit is one wot lives alone, far away from them wot talks too much or asks too many questions.
				;'Ermits are shy, quiet types wot don't say much.  Me brother 'Arry 'ardly says a word in a year.
				;Me sister 'Ortense 'asn't spoken since she were six.
				;I never talks at all.  Yes, we 'ermits nose how to keep a mouth shut."
			)
			(1
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sFamily of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 2)
				;"Me family lives pretty far apart from each other. We're all 'ermits, you know."
			)
			(1
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sEnry of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 3)
				;"I'm 'enry.  'Enry the eighth I is.  Me farther was an 'Enry and 'is farther was an 'Enry and 'is farther was an
				;'Enry and 'is farther was an 'Enry and 'is farther was an 'Enry and 'is farther was an 'Enry and 'is farther was an 'Enry.
				;And every one was an 'ermit."
			)
			(1
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sCrib of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 4)
				;"I likes to play the game.  I can show you how to play, if you likes."
			)
			(1
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sTrig of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 5)
				;"It's the spell wot sets off uther spells."
			)
			(1
				(Say (ScriptID 83 1) 145 6)
				;"Like when you wants to see the ladder, you casts a trigger and there you 'ave it, large as life."
			)
			(2
				(Say (ScriptID 83 1) 145 7)
				;"Course, you gots to 'ave a spell on the invisible ladder to make it visible first.  But it 'as its uses."
			)
			(3
				(Say (ScriptID 83 1) 145 8)
				;"I thinks I 'ave a scroll with the spell, if you be knowing sum magic, that is."
			)
			(4
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sSpell of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 9)
				;"The spells wot I nose is pretty simple maybe, but I like 'em."
			)
			(1
				(Say (ScriptID 83 1) 145 10)
				;"I can make me ladder show up and me door close and even get rid o' pests using the trigger spell, you see."
			)
			(2
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sLadder of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 11)
				;"I nose sum magic.  Erasmus the wizard tot me sum spells."
			)
			(1
				(Say (ScriptID 83 1) 145 12)
				;"He's wot put the spell on me ladder sos the brigands don't get up.  Them wot climbs th' rocks gets a door opening in their nose."
			)
			(2
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)

(instance sFalls of Script
	(properties)
	
	(method (dispose)
		((ScriptID 83 1) caller: 0)
		(super dispose:)
		(DisposeScript 145)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 83 1) caller: self)
				(HandsOff)
				(Say (ScriptID 83 1) 145 13)
				;"This 'ere's wot's called the Flying Falls."
			)
			(1
				(Say (ScriptID 83 1) 145 14)
				;"I've doon sum flying falls arund 'ere meself. That porch gets slippery sumtimes.  He, heh, he."
			)
			(2
				(client setScript: (ScriptID 83 2))
				(HandsOn)
			)
		)
	)
)
