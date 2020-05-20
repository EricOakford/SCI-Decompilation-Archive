;;; Sierra Script 1.0 - (do not remove this comment)
(script# JUNKBAY)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	junkBay 0
)

(instance junkBay of Locale
	(properties)
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					(
					(Said 'get,get[<up]/iron,scrap,debris,heap[<sharp]') (Print 700 0))
					(
						(Said
							'get,manipulate,feel/(edge[<jagged]),(programmer[<barbed])'
						)
						(Print 700 1)
					)
					((Said 'eat/iron,scrap,debris,heap[<sharp]') (Print 700 2))
					((Said 'expectorate') (Print 700 3))
					((Said 'write,swing') (Print 700 4))
					((Said 'listen') (Print 700 5))
					((Said 'kiss') (Print 700 6))
					((Said 'clean') (Print 700 7))
					((Said 'eat/ass') (Print 700 8))
					((Said 'beat') (Print 700 9))
					(
						(Said
							'press,manipulate,roll/iron,scrap,debris,heap[<sharp]'
						)
						(Print 700 10)
					)
					((Said 'holler,whistle,laugh[<out]') (Print 700 11))
					((Said 'cry[<out]') (Print 700 12))
					((Said 'use/cable') (if (ego has: 1) (Print 700 13) else (Print 700 14)))
					((Said 'tie') (Print 700 15))
					(
					(Said 'climb/iron,building,heap,debris,scrap[<sharp]') (Print 700 16))
					((Said 'use,climb,stand,erect,lean/ladder') (if (ego has: iLadder) (Print 700 17) else (DontHave)))
					((Said 'look>')
						(cond 
							(
								(or
									(Said '[<up,above,at]/ceiling')
									(Said '<up,above,at[/ceiling]')
								)
								(Print 700 18)
							)
							(
								(and
									(!= roomWithMotivator curRoomNum)
									(or
										(Said '[<down,below,at]/dirt,deck')
										(Said '<down,below,at[/dirt,deck]')
									)
								)
								(Print 700 19)
							)
							((Said '/console') (Print 700 20))
							((Said '/banister,banister,beam') (Print 700 21))
							((Said '/partition,console[<leech,e,north,w]') (Print 700 22))
							(
								(Said
									'[<in,at,through]/debris,heap,iron,garbage,end,debris,scrap'
								)
								(Print 700 23)
							)
							((Said '/(edge[<jagged]),(programmer[<barbed])') (Print 700 1))
							((Said '/dirt') (Print 700 24))
							((Said '/paint,coolant') (Print 700 25))
							((Said '/lamp') (Print 700 26))
							((Said '/conduit,frame') (Print 700 27))
							((Said '/console') (Print 700 28))
							((Said '/self') (Print 700 29))
							((Said '/appendage,head,leg,body,self') (Print 700 30))
						)
					)
					((Said 'nap,(lie<down)[/deck]') (Print 700 31))
					((Said '*/cable[<*]') (Print 700 32))
					((Said 'drop,hold[<in]/crystal[<glowing]/mouth') (Print 700 33))
					((Said 'eat,break/crystal[<glowing]') (Print 700 34))
					((Said 'dig[/cavity]') (Print 700 35))
					((Said 'climb/banister,beam,banister<up<to') (Print 700 36))
					((Said 'climb') (Print 700 37))
					((Said 'jump/craft') (Print 700 38))
					(
						(Said
							'hoist,open,manipulate,press,drag,bend/deck,console'
						)
						(Print 700 39)
					)
					((Said 'get/tile[<rib,deck]') (Print 700 40))
				)
				(if
					(and
						(== roomWithMotivator curRoomNum)
						(Said 'use,(turn<on)/motivator,artifact,device')
					)
					(Print 700 41)
				)
			)
		)
	)
)