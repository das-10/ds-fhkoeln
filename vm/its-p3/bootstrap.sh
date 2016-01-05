
function add_salt_minion_config_file(){
	cp /home/vagrant/sync/minion /etc/salt/
}


function provisionning(){
	salt-call --local state.highstate
}


add_salt_minion_config_file
provisionning